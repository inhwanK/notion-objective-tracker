package ind.venture.remindercore.service;

//import ind.venture.remindercore.domain.Database;

import com.fasterxml.jackson.databind.ObjectMapper;
import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.filter.FilterRequest;
import ind.venture.remindercore.domain.property.DatabaseProperty;
import ind.venture.remindercore.domain.filter.DateFilter;
import ind.venture.remindercore.domain.filter.PropertyFilter;
import ind.venture.remindercore.domain.query.QueryResults;
import notion.api.v1.json.GsonSerializer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NotionDatabaseService {

    private GsonSerializer gsonSerializer;
    private final WebClient notionClient;
    private static final String DATABASE_URI = "/databases/";

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
        DateFilter dateFilter = DateFilter.builder()
                .notEmpty(true)
                .build();

        PropertyFilter propertyFilter = new PropertyFilter("리마인더", dateFilter);
        FilterRequest filterRequest = new FilterRequest(propertyFilter);

        return notionClient.post()
                .uri(DATABASE_URI + databaseId + "/query")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(filterRequest)
                .retrieve()
                .bodyToMono(QueryResults.class)
                .map(QueryResults::getResults);
    }
}
