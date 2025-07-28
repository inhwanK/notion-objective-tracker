package ind.venture.objectivenotionservice.controller;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.service.ObjectiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NotionWebhooksReceiverController {

    private final ObjectiveService objectiveService;

    //  웹훅 인증시에는 주석 처리하여 빌드
    @PostMapping("/webhook/event")
    public Mono<Void> receiveEvent(@RequestBody NotionWebhookEvent event) {
        log.info("Received webhook event: {}", event);
        if ("page.properties_updated".equals(event.getType())) {
            return objectiveService.createSubObjective(event);
        }
        return Mono.empty();
    }

    // 노션 웹훅 등록 시 사용
    /*
    @PostMapping("/webhook/event")
    public String receiveEvent(@RequestBody String validation_token) {
        log.info("Received webhook validation token: {}", validation_token);
        return validation_token;
    }
    */
}
