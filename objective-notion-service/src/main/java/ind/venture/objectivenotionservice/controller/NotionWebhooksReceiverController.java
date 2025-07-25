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

    private final ObjectMapper objectMapper;
    private final ObjectiveService objectiveService;

    @PostMapping("/webhook/event")
    public Mono<Void> receiveEvent(@RequestBody String body) {
        try {
            JsonNode root = objectMapper.readTree(body);

            // 웹훅 등록 시 verification_token 확인하기
            if (root.has("verification_token")) {
                log.info("Webhook verification_token: {}", root.get("verification_token").asText());
                return Mono.empty();
            }

            // 그 외에는 NotionWebhookEvent
            NotionWebhookEvent event = objectMapper.treeToValue(root, NotionWebhookEvent.class);
            log.info("Received webhook event: {}", event);

            if ("page.properties_updated".equals(event.getType())) {
                return objectiveService.createSubObjective(event);
            }
        } catch (Exception e) {
            log.error("Failed to handle webhook event: {}", e.getMessage(), e);
        }
        return Mono.empty();
    }
}
