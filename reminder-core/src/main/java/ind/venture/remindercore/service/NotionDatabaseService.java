package ind.venture.remindercore.service;


import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.property.DatabaseProperty;
import ind.venture.remindercore.domain.query.QueryResults;
import ind.venture.remindercore.request.DatabaseRequest;
import ind.venture.remindercore.util.DatabaseRequestFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class NotionDatabaseService {

    private final WebClient notionClient;
    private static final String DATABASE_URI = "databases";

    public NotionDatabaseService(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    public Mono<Database> getDatabaseInfo(String apiKey, String databaseId) {
        return fetchDatabase(apiKey, databaseId);
    }

    public Mono<Boolean> checkIsReminderDatabase(String apiKey, String databaseId) {
        return fetchDatabase(apiKey, databaseId)
                .doOnSubscribe(s -> log.info("[리마인더 확인 요청] databaseId: {}", databaseId))
                .map(this::hasReminderProperty)
                .doOnNext(result -> log.info("[리마인더 확인 결과] databaseId: {}, hasReminder: {}", databaseId, result))
                .doOnError(e -> log.error("[리마인더 요청 실패] databaseId: {}, error: {}", databaseId, e.getMessage()));
    }

    public Mono<List<Page>> findAllReminderPage(String apiKey, String databaseId) {
        return queryReminderPage(apiKey, databaseId, DatabaseRequestFactory.createNotEmptyReminderRequest());
    }

    public Mono<List<Page>> findTodayReminderPage(String apiKey, String databaseId) {
        return queryReminderPage(apiKey, databaseId, DatabaseRequestFactory.createTodayReminderRequest());
    }

    public Mono<List<Page>> findWeeklyReminderPage(String apiKey, String databaseId) {
        return queryReminderPage(apiKey, databaseId, DatabaseRequestFactory.createWeeklyReminderRequest());
    }

    private boolean hasReminderProperty(Database database) {
        DatabaseProperty reminderProperty = database.getProperties().get("리마인더");
        return reminderProperty != null && "date".equals(reminderProperty.getType());
    }

    private Mono<Database> fetchDatabase(String apiKey, String databaseId) {
        return notionClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(DATABASE_URI, databaseId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class);
    }

    private Mono<List<Page>> queryReminderPage(
            String apiKey,
            String databaseId,
            DatabaseRequest databaseRequest
    ) {
        return notionClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment(DATABASE_URI, databaseId, "query").build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }
}
