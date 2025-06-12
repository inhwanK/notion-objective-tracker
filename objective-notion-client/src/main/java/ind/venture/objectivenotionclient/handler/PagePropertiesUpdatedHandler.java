package ind.venture.objectivenotionclient.handler;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionclient.service.NotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagePropertiesUpdatedHandler implements WebhookEventHandler {

    private final NotionService notionService;

    public PagePropertiesUpdatedHandler(NotionService notionService) {
        this.notionService = notionService;
    }

    @Override
    public void handle(String apiKey, NotionWebhookEvent event) {
        // 페이지 조회
            // 해당 페이지 속성 검사
                // '목표 생성 일시', date 타입 검증
    }

    // TODO '목표 생성 일시', date 타입 검증 메서드 생성

    @Override
    public String getType() {
        return "page.properties_updated";
    }
}
