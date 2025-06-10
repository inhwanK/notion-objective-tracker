package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionclient.handler.PagePropertiesUpdatedHandler;
import ind.venture.objectivenotionclient.handler.WebhookEventDispatcher;
import ind.venture.objectivenotionclient.handler.WebhookEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    @Value("${notion.api.key}")
    private String apiKey;
    private final WebhookEventDispatcher webhookEventDispatcher;

    public NotionWebhooksReceiverController(WebhookEventDispatcher webhookEventDispatcher) {
        this.webhookEventDispatcher = webhookEventDispatcher;
    }

    @PostMapping("/webhook/event")
    public void receiveEvent(
            @RequestBody NotionWebhookEvent event
    ) {
        log.info("event: {}", event);
        webhookEventDispatcher.dispatch(apiKey, event);
    }
}
