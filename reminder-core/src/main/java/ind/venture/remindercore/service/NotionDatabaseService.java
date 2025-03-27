package ind.venture.remindercore.service;


import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.property.DatabaseProperty;
import ind.venture.remindercore.domain.query.QueryResults;
import ind.venture.remindercore.request.DatabaseRequest;
import ind.venture.remindercore.request.filter.FilterRequest;
import ind.venture.remindercore.util.DatabaseRequestFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NotionDatabaseService {
    
    private final WebClient notionClient;
    private static final String DATABASE_URI = "/databases";

    public NotionDatabaseService(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    public Mono<Database> getDatabaseInfo(String apiKey, String databaseId) {
        return notionClient.get()
                .uri(DATABASE_URI + databaseId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class);
    }


    public Mono<Boolean> checkHasReminderProperty(String apiKey, String databaseId) {
        return notionClient.get()
                .uri(DATABASE_URI + databaseId)
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
        DatabaseRequest filterRequest = DatabaseRequestFactory.createNotEmptyFilterRequest();

        return findReminderPage(apiKey, databaseId, filterRequest);
    }

    public Mono<List<Page>> findTodayReminderPage(String apiKey, String databaseId) {
        DatabaseRequest databaseRequest = DatabaseRequestFactory.createTodayFilterRequest();

        return findReminderPage(apiKey, databaseId, databaseRequest);
    }

    public Mono<List<Page>> findWeeklyReminderPage(String apiKey, String databaseId) {
        DatabaseRequest filterRequest = DatabaseRequestFactory.createWeeklyFilterRequest();

        return findReminderPage(apiKey, databaseId, filterRequest);
    }

    public Mono<List<Page>> findReminderPage(
            String apiKey,
            String databaseId,
            DatabaseRequest databaseRequest
    ) {
        return notionClient.post()
                .uri(createUri(databaseId))
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(databaseRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }

    public String createUri(String databaseId) {
        return UriComponentsBuilder
                .fromUriString(DATABASE_URI)
                .pathSegment(databaseId)
                .path("/query")
                .build()
                .toUriString();
    }
}
