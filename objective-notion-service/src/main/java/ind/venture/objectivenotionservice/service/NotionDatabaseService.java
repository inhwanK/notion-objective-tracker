package ind.venture.objectivenotionservice.service;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionservice.client.NotionDatabaseClient;
import ind.venture.objectivenotionservice.util.DatabaseRequestFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class NotionDatabaseService {
    private final NotionDatabaseClient notionDatabaseClient;

    public NotionDatabaseService(NotionDatabaseClient notionDatabaseClient) {
        this.notionDatabaseClient = notionDatabaseClient;
    }

    public Mono<Database> getDatabaseInfo(String apiKey, String databaseId) {
        return logWithContext("데이터베이스 정보 조회:" + databaseId,
                notionDatabaseClient.fetchDatabase(apiKey, databaseId)
        );
    }

    public Mono<Boolean> checkIsReminderDatabase(String apiKey, String databaseId) {
        return logWithContext("리마인더 속성 여부:" + databaseId,
                notionDatabaseClient.fetchDatabase(apiKey, databaseId)
                        .map(this::hasReminderProperty)
        );
    }

    public Mono<List<Page>> findAllReminderPage(String apiKey, String databaseId) {
        return logWithContext("전체 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createNotEmptyReminderRequest())
        );
    }

    public Mono<List<Page>> findWeeklyReminderPage(String apiKey, String databaseId) {
        return logWithContext("주간 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createWeeklyReminderRequest())
        );
    }

    public Mono<List<Page>> findTodayReminderPage(String apiKey, String databaseId) {
        return logWithContext("당일 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(apiKey, databaseId, DatabaseRequestFactory.createTodayReminderRequest())
        );
    }

    private boolean hasReminderProperty(Database database) {
        DatabaseProperty reminderProperty = database.getProperties().get("리마인더");
        return reminderProperty != null && "date".equals(reminderProperty.getType());
    }

    private <T> Mono<T> logWithContext(String context, Mono<T> mono) {
        return mono
                .doOnSubscribe(s -> log.info("[{}] 요청 시작", context))
                .doOnNext(res -> log.info("[{}] 응답: {}", context, res))
                .doOnError(err -> log.error("[{}] 에러: {}", context, err.getMessage()));
    }
}
