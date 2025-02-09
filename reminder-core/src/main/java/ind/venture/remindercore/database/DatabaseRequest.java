package ind.venture.remindercore.database;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DatabaseRequest {

    private final WebClient webClient;
    private static final String DATABASE_URI = "/databases/";

    public DatabaseRequest(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Database> retrieveDatabaseInfo(String ApiKey, String databaseId) {
        Mono<Database> request = webClient.get()
                .uri(DATABASE_URI + databaseId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + ApiKey)
                .retrieve()
                .bodyToMono(Database.class);

        return request;
    }

}
