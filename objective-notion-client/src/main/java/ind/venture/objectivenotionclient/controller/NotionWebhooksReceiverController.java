package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEventDto;
import ind.venture.objectivenotionclient.handler.PagePropertiesUpdatedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    @Value("${notion.api.key}")
    private String apiKey;
    private final PagePropertiesUpdatedHandler pagePropertiesUpdatedHandler;

    public NotionWebhooksReceiverController(PagePropertiesUpdatedHandler pagePropertiesUpdatedHandler) {
        this.pagePropertiesUpdatedHandler = pagePropertiesUpdatedHandler;
    }

    @PostMapping("/webhook/event")
    public void receiveEvent(
            @RequestBody NotionWebhookEventDto notionWebhookEventDto
    ) {
        log.info("notionWebhookEventDto: {}", notionWebhookEventDto);
        if ("page.properties_updated".equals(notionWebhookEventDto.getType())) {
            pagePropertiesUpdatedHandler.handle(apiKey, notionWebhookEventDto);
        }
    }


}
