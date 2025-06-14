package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import reactor.core.publisher.Mono;

public interface NotionPageClient {
    Mono<Page> fetchPage(String apiKey, String pageId);
    Mono<PageProperty> fetchPageProperty(String apiKey, String pageId, String propertyId);
}
