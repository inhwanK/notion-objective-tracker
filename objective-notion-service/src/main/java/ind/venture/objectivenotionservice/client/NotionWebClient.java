package ind.venture.objectivenotionservice.client;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.QueryResults;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.request.QueryDatabaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class NotionWebClient implements NotionDatabaseClient, NotionPageClient {

    private final WebClient webClient;
    private static final String DATABASE_URI = "databases";
    private static final String PAGE_URI = "pages";

    public NotionWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Database> fetchDatabase(String apiKey, String databaseId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId).build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class);
    }

    @Override
    public Mono<List<Page>> queryDatabase(String apiKey, String databaseId, QueryDatabaseRequest databaseRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId, "query").build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }

    @Override
    public Mono<Page> fetchPage(String apiKey, String pageId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(PAGE_URI, pageId).build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Page.class);
    }

    @Override
    public Mono<Page> deletePage(String apiKey, String pageId) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.pathSegment(PAGE_URI, pageId).build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of("archived", true))
                .retrieve()
                .bodyToMono(Page.class);
    }

    @Override
    public Mono<PageProperty> fetchPageProperty(String apiKey, String pageId, String propertyId) {

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.pathSegment(PAGE_URI, pageId, "properties", propertyId).build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(PageProperty.class);
    }

}
