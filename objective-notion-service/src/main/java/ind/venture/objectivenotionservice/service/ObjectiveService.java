package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.page.property.DateProperty;
import ind.venture.objectivenotion.model.page.type.Date;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.client.NotionPageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Slf4j
@Service
public class ObjectiveService {
    private final NotionPageClient notionPageClient;

    public ObjectiveService(NotionPageClient notionPageClient) {
        this.notionPageClient = notionPageClient;
    }

    public Mono<Void> createSubObjective(String apiKey, NotionWebhookEvent event) {
        return findSubObjectiveCreatedAtPropertyId(apiKey, event)
                .filter(this::validatePossibleCreateSubObjective)
                .then();
    }


    public Mono<DateProperty> findSubObjectiveCreatedAtPropertyId(String apiKey, NotionWebhookEvent event) {
        String pageId = event.getEntity().getId();
        String targetName = "하위 목표 생성 시간";
        return notionPageClient.fetchPage(apiKey, pageId)
                .map(page -> (DateProperty) page.getProperties().get(targetName));
    }

    private boolean validatePossibleCreateSubObjective(DateProperty property) {
        if (property == null) {
            log.warn("하위 목표 생성 시간 속성이 존재하지 않습니다."); // 추후 데이터베이스 속성을 자동으로 추가하도록 하기
            return false;
        }

        Date date = property.getDate();
        String createdAt = (date != null) ? date.getStart() : null;

        return createdAt == null || OffsetDateTime.parse(createdAt)
                .isBefore(OffsetDateTime.now().minusMinutes(1));
    }
}
