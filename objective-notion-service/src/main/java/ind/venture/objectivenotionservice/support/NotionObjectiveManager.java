package ind.venture.objectivenotionservice.support;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.model.page.property.RelationProperty;
import ind.venture.objectivenotion.model.page.property.TitleProperty;
import ind.venture.objectivenotion.model.page.type.Relation;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.client.NotionPageClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotionObjectiveManager {
    private final NotionPageClient notionPageClient;

    public Mono<Page> createSubPage(String databaseId, String parentPageId, String title) {
        return notionPageClient.createSubPage(databaseId, parentPageId, title);
    }

    public Mono<Void> deleteAllSubObjectives(Page page) {
        List<Relation> relations = extractSubRelations(page);
        return Flux.fromIterable(relations)
                .flatMap(r -> notionPageClient.deletePage(r.getId()))
                .then();
    }

    private List<Relation> extractSubRelations(Page page) {
        PageProperty prop = page.getProperties().get("하위 항목");
        if (prop instanceof RelationProperty) {
            List<Relation> relations = ((RelationProperty) prop).getRelation();
            log.info("relations: {}", relations);
            return (relations != null) ? relations : Collections.emptyList();
        }
        return Collections.emptyList();
    }

    public String extractMainObjectiveTitle(Page page) {
        TitleProperty titleProperty = (TitleProperty) page.getProperties().get("목표");
        return titleProperty.getTitle().get(0).getText().getContent();
    }

    public Mono<Page> getCreatableSubObjectivePage(NotionWebhookEvent event) {
        String pageId = event.getEntity().getId();
        List<String> properties = event.getData().getUpdatedProperties();

        return notionPageClient.fetchPage(pageId)
                .doOnNext(notionPage -> log.info("notionPage {}", notionPage))
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
