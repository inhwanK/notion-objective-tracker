package ind.venture.objectivenotionservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.service.ObjectiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    private final ObjectiveService objectiveService;

    @PostMapping("/webhook/event")
    public Mono<Void> receiveEvent(@RequestBody NotionWebhookEvent event) {
        log.info("Received webhook event: {}", event);
        if ("page.properties_updated".equals(event.getType())) {
            return objectiveService.createSubObjective(event);
        }
        return Mono.empty();
    }
}
