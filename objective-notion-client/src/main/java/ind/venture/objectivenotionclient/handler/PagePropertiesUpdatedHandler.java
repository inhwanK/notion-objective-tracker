package ind.venture.objectivenotionclient.handler;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEventDto;
import ind.venture.objectivenotionclient.service.NotionService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PagePropertiesUpdatedHandler implements WebhookEventHandler {

    private final NotionService notionService;

    public PagePropertiesUpdatedHandler(NotionService notionService) {
        this.notionService = notionService;
    }

    @Override
    public void handle(String apiKey, NotionWebhookEventDto eventDto) {
        Mono<Page> updatedPage = notionService.findPageById(apiKey, eventDto.getId());
    }
}
