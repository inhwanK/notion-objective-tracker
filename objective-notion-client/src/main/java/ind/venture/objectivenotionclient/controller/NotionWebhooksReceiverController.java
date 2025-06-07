package ind.venture.objectivenotionclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    @PostMapping("/webhook/event")
    public void propertiesUpdate(
            @RequestBody Map<String, String> data
//            @RequestBody NotionWebhookEventDto notionWebhookEventDto
    ) {
//        log.info("notionWebhookEventDto: {}", notionWebhookEventDto);
        log.info("Received verification token: {}", data.get("verification_token"));
        return;
    }

    @GetMapping("/healthy")
    public String healthy() {
        log.info("healthy check");
        return "ok";
    }
}
