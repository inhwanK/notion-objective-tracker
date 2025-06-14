package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotion.model.page.Page;
import reactor.core.publisher.Mono;

public interface NotionPageClient {
    Mono<Page> fetchPage(String apiKey, String pageId);
}
