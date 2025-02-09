package ind.venture.remindercore;

import ind.venture.remindercore.database.Database;
import ind.venture.remindercore.database.DatabaseRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class DatabaseRequestTest {

    @Autowired
    private WebClient webClient;

    @Value("${notion.api.key}")
    private String notionApiKey;

    @Value("${notion.database.url}")
    private String notionDatabaseUrl;

    @Test
    public void retrieveDatabaseInfo() {
        DatabaseRequest databaseRequest = new DatabaseRequest(webClient);

        Mono<Database> response = databaseRequest
                .retrieveDatabaseInfo(notionApiKey, notionDatabaseUrl);

        StepVerifier.create(response, 1)
                .expectSubscription()
                .assertNext(database -> {
                    Assertions.assertNotNull(database);
                    Assertions.assertTrue(database.isInline());
                })
                .verifyComplete();
    }
}
