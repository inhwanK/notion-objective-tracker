package ind.venture.remindercore.config;

import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.query.QueryResults;
import ind.venture.remindercore.request.DatabaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class NotionWebClient implements NotionClient {

    private final WebClient webClient;
    private static final String DATABASE_URI = "databases";

    public NotionWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Database> fetchDatabase(String apiKey, String databaseId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(DATABASE_URI, databaseId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class);
    }

    @Override
    public Mono<List<Page>> queryDatabase(String apiKey, String databaseId, DatabaseRequest databaseRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId, "query").build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .doOnSubscribe(sub -> log.info("Notion DB 쿼리 요청: databaseId={}, request={}", databaseId, databaseRequest))
                .doOnNext(result -> log.info("Notion DB 쿼리 응답: {}", result))
                .doOnError(error -> log.info("Notion DB 쿼리 실패: {}", error.getMessage(), error))
                .map(QueryResults::getResults);
    }
}
