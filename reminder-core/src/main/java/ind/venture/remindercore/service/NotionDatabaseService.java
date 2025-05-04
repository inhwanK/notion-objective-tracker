package ind.venture.remindercore.service;


import ind.venture.remindercore.config.NotionClient;
import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.property.DatabaseProperty;
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
    private final NotionClient notionClient;

    public NotionDatabaseService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    public Mono<Database> getDatabaseInfo(String apiKey, String databaseId) {
        return notionClient.fetchDatabase(apiKey, databaseId);
    }

    public Mono<Boolean> checkIsReminderDatabase(String apiKey, String databaseId) {
        return notionClient.fetchDatabase(apiKey, databaseId)
                .doOnSubscribe(s -> log.info("[리마인더 확인 요청] databaseId: {}", databaseId))
                .map(this::hasReminderProperty)
                .doOnNext(result -> log.info("[리마인더 확인 결과] databaseId: {}, hasReminder: {}", databaseId, result))
                .doOnError(e -> log.error("[리마인더 요청 실패] databaseId: {}, error: {}", databaseId, e.getMessage()));
    }

    public Mono<List<Page>> findAllReminderPage(String apiKey, String databaseId) {
        return notionClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createNotEmptyReminderRequest());
    }

    public Mono<List<Page>> findTodayReminderPage(String apiKey, String databaseId) {
        return notionClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createTodayReminderRequest());
    }

    public Mono<List<Page>> findWeeklyReminderPage(String apiKey, String databaseId) {
        return notionClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createWeeklyReminderRequest());
    }

    private boolean hasReminderProperty(Database database) {
        DatabaseProperty reminderProperty = database.getProperties().get("리마인더");
        return reminderProperty != null && "date".equals(reminderProperty.getType());
    }
}
