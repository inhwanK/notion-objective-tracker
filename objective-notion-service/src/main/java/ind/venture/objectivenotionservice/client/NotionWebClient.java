package ind.venture.objectivenotionservice.client;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.QueryResults;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.request.CreatePageRequest;
import ind.venture.objectivenotion.request.QueryDatabaseRequest;
import ind.venture.objectivenotionservice.util.PageRequestFactory;
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

    private final WebClient notionClient;
    private static final String DATABASE_URI = "databases";
    private static final String PAGE_URI = "pages";

    public NotionWebClient(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    @Override
    public Mono<Database> fetchDatabase(String databaseId) {
        return notionClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Database.class);
    }

    @Override
    public Mono<List<Page>> queryDatabase(String databaseId, QueryDatabaseRequest databaseRequest) {
        return notionClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId, "query").build())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }

    @Override
    public Mono<Page> fetchPage(String pageId) {
        return notionClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(PAGE_URI, pageId).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Page.class);
    }

    @Override
    public Mono<Page> createSubPage(String databaseId, String parentPageId, String title) {

        CreatePageRequest requestBody = PageRequestFactory.createSubPageRequest(
                databaseId,
                title,
                parentPageId
        );

        try {
            String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(requestBody);
            log.info("Notion 페이지 생성 요청 바디: {}", json);
        } catch (Exception e) {
            log.warn("요청 바디 직렬화 실패", e);
        }

        return notionClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment(PAGE_URI).build())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Page.class);
    }

    @Override
    public Mono<Page> deletePage(String pageId) {
        return notionClient.patch()
                .uri(uriBuilder -> uriBuilder.pathSegment(PAGE_URI, pageId).build())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("archived", true))
                .retrieve()
                .bodyToMono(Page.class);
    }

    @Override
    public Mono<PageProperty> fetchPageProperty(String pageId, String propertyId) {
        return notionClient.get()
                .uri(uriBuilder ->
                        uriBuilder.pathSegment(PAGE_URI, pageId, "properties", propertyId).build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PageProperty.class);
    }

}
