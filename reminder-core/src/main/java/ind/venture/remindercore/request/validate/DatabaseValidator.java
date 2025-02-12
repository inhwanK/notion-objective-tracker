package ind.venture.remindercore.request.validate;


import ind.venture.remindercore.domain.Database;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DatabaseValidator {

    private final WebClient notionClient;
    private static final String DATABASE_URI = "/databases/";

    public DatabaseValidator(WebClient notionClient) {
        this.notionClient = notionClient;
    }

    public void validateDatabase(String apiKey, String databaseId) {
        Mono<Database> response = retrieveDatabaseInfo(apiKey, databaseId);
        response.subscribe(database -> {
            if(!database.isReminder()) {
                throw new RuntimeException("Database is not reminder");
            }
        });
    }

    private Mono<Database> retrieveDatabaseInfo(String apiKey, String databaseId) {
        return notionClient.get()
                .uri(DATABASE_URI + databaseId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(Database.class);
    }

}
