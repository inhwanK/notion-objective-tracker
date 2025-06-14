package ind.venture.objectivenotionservice.controller;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.service.NotionPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    @Value("${notion.api.key}")
    private String apiKey;
    private final NotionPageService notionPageService;

    public NotionWebhooksReceiverController(NotionPageService notionPageService) {
        this.notionPageService = notionPageService;
    }

    @PostMapping("/webhook/event")
    public Mono<Void> receiveEvent(
            @RequestBody NotionWebhookEvent event
    ) {
        if ("page.properties_updated".equals(event.getType())) {
            log.info("[웹훅] 속성 업데이트 : {}", event);
            return Mono.empty();
        }
        log.info("[웹훅] 지원하지 않는 이벤트 : {}", event);
        return Mono.empty();
    }
}
