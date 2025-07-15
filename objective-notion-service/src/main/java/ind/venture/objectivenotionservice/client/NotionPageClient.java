package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import reactor.core.publisher.Mono;

public interface NotionPageClient {
    Mono<Page> fetchPage(String pageId);
    Mono<Page> deletePage(String pageId);
    Mono<PageProperty> fetchPageProperty(String pageId, String propertyId);

}
