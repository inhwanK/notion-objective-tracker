package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.model.page.property.RelationProperty;
import ind.venture.objectivenotion.model.page.type.Relation;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.client.NotionPageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ObjectiveService {
    private final NotionPageClient notionPageClient;

    public ObjectiveService(NotionPageClient notionPageClient) {
        this.notionPageClient = notionPageClient;
    }

    public Mono<Void> createSubObjective(String apiKey, NotionWebhookEvent event) {
        return getCreatableSubObjectivePage(apiKey, event)
                .flatMap(page -> {
                    List<Relation> relations = extractSubRelations(page);
                    // 하위 목표 제거
                    return Flux.fromIterable(relations)
                            .flatMap(relation -> notionPageClient.deletePage(apiKey, relation.getId()))
                            .then();
                });
    }

    private List<Relation> extractSubRelations(Page page) {
        PageProperty prop = page.getProperties().get("하위 항목");
        if (prop instanceof RelationProperty) {
            List<Relation> relations = ((RelationProperty) prop).getRelation();
            return (relations != null) ? relations : Collections.emptyList();
        }
        return Collections.emptyList();
    }


    public Mono<Page> getCreatableSubObjectivePage(String apiKey, NotionWebhookEvent event) {
        String pageId = event.getEntity().getId();
        List<String> properties = event.getData().getUpdatedProperties();

        return notionPageClient.fetchPage(apiKey, pageId)
                .filter(page -> validateRelation(page) && validateSubObjectiveCreatedAt(page.getProperties(), properties))
                .switchIfEmpty(Mono.error(new IllegalStateException("하위 목표 생성 조건이 아닙니다.")));
    }

    private boolean validateRelation(Page page) {
        Map<String, PageProperty> props = page.getProperties();
        PageProperty sub = props.get("하위 항목");
        PageProperty parent = props.get("상위 항목");

        return sub != null
                && parent != null
                && "relation".equals(sub.getType())
                && "relation".equals(parent.getType());
    }

    private boolean validateSubObjectiveCreatedAt(Map<String, PageProperty> properties, List<String> updatedProperties) {
        PageProperty property = properties.get("하위 목표 생성 시간");
        return property != null && updatedProperties.contains(property.getId());
    }
}
