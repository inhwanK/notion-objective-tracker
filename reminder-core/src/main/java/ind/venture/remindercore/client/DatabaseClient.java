package ind.venture.remindercore.client;

import ind.venture.remindercore.model.Database;
import ind.venture.remindercore.model.filter.DateFilter;
import ind.venture.remindercore.model.filter.PropertyFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DatabaseClient {

    private final WebClient notionClient;
    private static final String DATABASE_URI = "/databases/";

    public DatabaseClient(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    public void retrieveDatabaseInfo(String apiKey, String databaseId) {
        Mono<Database> response = notionClient.get()
                .uri(DATABASE_URI + databaseId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class)
                .flatMap(database -> {
                    if (!database.isReminder()) {
                        return Mono.error(new RuntimeException("Database is not reminder"));
                    }
                    return Mono.just(database);
                });

        response.subscribe(System.out::println);
    }

    public Mono<Void> retrieveReminderPage(String apiKey, String databaseId) {
        DateFilter dateFilter = DateFilter.builder()
                .isEmpty(true)
                .build();

        return notionClient.post()
                .uri(DATABASE_URI + databaseId + "/query")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(new PropertyFilter("리마인더", dateFilter))
                .retrieve()
                .bodyToMono(Void.class);
    }

}
