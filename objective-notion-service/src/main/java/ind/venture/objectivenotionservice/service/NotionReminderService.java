package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionservice.client.NotionDatabaseClient;
import ind.venture.objectivenotionservice.util.DatabaseRequestFactory;
import ind.venture.objectivenotionservice.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class NotionReminderService {
    private final NotionDatabaseClient notionDatabaseClient;

    public NotionReminderService(NotionDatabaseClient notionDatabaseClient) {
        this.notionDatabaseClient = notionDatabaseClient;
    }


    public Mono<List<Page>> findAllReminderPage(String databaseId) {
        return LogUtils.logWithContext("전체 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(databaseId, DatabaseRequestFactory.createNotEmptyReminderRequest())
        );
    }

    public Mono<List<Page>> findWeeklyReminderPage(String databaseId) {
        return LogUtils.logWithContext("주간 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(databaseId, DatabaseRequestFactory.createWeeklyReminderRequest())
        );
    }

    public Mono<List<Page>> findTodayReminderPage(String databaseId) {
        return LogUtils.logWithContext("당일 리마인더 조회:" + databaseId,
                notionDatabaseClient.queryDatabase(databaseId, DatabaseRequestFactory.createTodayReminderRequest())
        );
    }
}
