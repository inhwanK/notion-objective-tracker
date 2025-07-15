package ind.venture.objectivenotionservice.service;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotionservice.client.NotionDatabaseClient;
import ind.venture.objectivenotionservice.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class NotionDatabaseService {
    private final NotionDatabaseClient notionDatabaseClient;

    public NotionDatabaseService(NotionDatabaseClient notionDatabaseClient) {
        this.notionDatabaseClient = notionDatabaseClient;
    }

    public Mono<Database> getDatabaseInfo(String databaseId) {
        return LogUtils.logWithContext("데이터베이스 정보 조회:" + databaseId,
                notionDatabaseClient.fetchDatabase(databaseId)
        );
    }

    public Mono<Boolean> checkIsReminderDatabase(String databaseId) {
        return LogUtils.logWithContext("리마인더 속성 여부:" + databaseId,
                notionDatabaseClient.fetchDatabase(databaseId)
                        .map(this::hasReminderProperty)
        );
    }

    private boolean hasReminderProperty(Database database) {
        DatabaseProperty reminderProperty = database.getProperties().get("리마인더");
        return reminderProperty != null && "date".equals(reminderProperty.getType());
    }
}
