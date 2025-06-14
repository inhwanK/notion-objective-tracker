package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.client.NotionPageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Slf4j
@Service
public class ObjectiveService {
    private final NotionPageClient notionPageClient;

    public ObjectiveService(NotionPageClient notionPageClient) {
        this.notionPageClient = notionPageClient;
    }

    public Mono<Void> createSubObjective(String apiKey, NotionWebhookEvent event) {
        return findSubObjectiveCreatedAtPropertyId(apiKey, event)
                .filter(this::canCreateSubObjective)
                .then();
    }


    public Mono<PageProperty> findSubObjectiveCreatedAtPropertyId(String apiKey, NotionWebhookEvent event) {
        String pageId = event.getEntity().getId();
        String targetName = "하위 목표 생성 시간";
        return notionPageClient.fetchPage(apiKey, pageId)
                .map(page -> page.getProperties().get(targetName))
                .filter(Objects::nonNull);
    }

    private boolean canCreateSubObjective(PageProperty property) {
        String subObjectiveCreatedAt = property.getDate().getStart();
        if (subObjectiveCreatedAt == null) {
            return true;
        }
        OffsetDateTime createdAt = OffsetDateTime.parse(subObjectiveCreatedAt);
        return createdAt.isBefore(OffsetDateTime.now().minusMinutes(1));
    }
}
