package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    @PostMapping("/webhook/event")
    public void propertiesUpdate(
            @RequestBody NotionWebhookEventDto notionWebhookEventDto
    ) {
        log.info("notionWebhookEventDto: {}", notionWebhookEventDto);

        return;
    }

    @GetMapping("/healthy")
    public String healthy() {
        log.info("healthy check");
        return "ok";
    }
}
