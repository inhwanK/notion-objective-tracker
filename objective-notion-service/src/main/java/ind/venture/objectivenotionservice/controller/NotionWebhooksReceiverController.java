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
        log.info("event: {}", event);
        return Mono.empty();
    }
}
