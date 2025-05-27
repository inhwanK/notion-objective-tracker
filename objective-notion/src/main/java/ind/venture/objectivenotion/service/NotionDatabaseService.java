package ind.venture.objectivenotion.service;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotion.model.database.QueryResults;
import ind.venture.objectivenotion.request.DatabaseRequest;
import ind.venture.objectivenotion.util.DatabaseRequestFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NotionDatabaseService {

    private final WebClient notionClient;
    private static final String DATABASE_URI = "databases";

    public NotionDatabaseService(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    public Mono<Database> getDatabaseInfo(String apiKey, String databaseId) {
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


    public Mono<Boolean> checkHasReminderProperty(String apiKey, String databaseId) {
        return notionClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(DATABASE_URI, databaseId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class)
                .map(this::isReminder);
    }

    private boolean isReminder(Database database) {
        if (!database.getProperties().containsKey("리마인더")) {
            return false;
        }
        DatabaseProperty reminder = database.getProperties().get("리마인더");
        return "date".equals(reminder.getType());
    }

    public Mono<List<Page>> findAllReminderPage(String apiKey, String databaseId) {
        DatabaseRequest databaseRequest = DatabaseRequestFactory.createNotEmptyReminderRequest();

        return findReminderPage(apiKey, databaseId, databaseRequest);
    }

    public Mono<List<Page>> findTodayReminderPage(String apiKey, String databaseId) {
        DatabaseRequest databaseRequest = DatabaseRequestFactory.createTodayReminderRequest();

        return findReminderPage(apiKey, databaseId, databaseRequest);
    }

    public Mono<List<Page>> findWeeklyReminderPage(String apiKey, String databaseId) {
        DatabaseRequest databaseRequest = DatabaseRequestFactory.createWeeklyReminderRequest();

        return findReminderPage(apiKey, databaseId, databaseRequest);
    }

    public Mono<List<Page>> findReminderPage(
            String apiKey,
            String databaseId,
            DatabaseRequest databaseRequest
    ) {
        return notionClient.post()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(DATABASE_URI, databaseId, "query")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }
}
