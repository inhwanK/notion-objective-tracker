package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotionservice.client.NotionDatabaseClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension .class)
public class NotionDatabaseServiceTests {

    @InjectMocks
    private NotionDatabaseService notionDatabaseService;

    @Mock
    private NotionDatabaseClient notionDatabaseClient;

    @BeforeEach
    public void setUp() {
        notionDatabaseService = new NotionDatabaseService(notionDatabaseClient);
    }


    @Test
    void getDatabaseInfo_Success() {
        Database db = new Database(
                "db-id",
                "https://notion.so/db-id",
                "https://notion.so/public/db-id",
                "2024-01-01T00:00:00.000Z",
                "2024-01-02T00:00:00.000Z",
                true,
                false,
                "req-id",
                false,
                Map.of("title", new DatabaseProperty("title", "Title", "text"))
        );

        when(notionDatabaseClient.fetchDatabase("token", "db-id"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.getDatabaseInfo("token", "db-id"))
                .expectNext(db)
                .verifyComplete();
    }

    @Test
    void getDatabaseInfo_400Error() {
        WebClientResponseException badRequestException = WebClientResponseException.create(
                400, "Bad Request", null, null, null);

        when(notionDatabaseClient.fetchDatabase("token", "db-id"))
                .thenReturn(Mono.error(badRequestException));

        StepVerifier.create(notionDatabaseService.getDatabaseInfo("token", "db-id"))
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException ex &&
                                ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                .verify();
    }

    @Test
    void checkIsReminderDatabase_ShouldReturnTrue() {
        Database db = new Database("db123", Map.of("리마인더", new DatabaseProperty("1", "리마인더", "date")));

        when(notionDatabaseClient.fetchDatabase("token", "db123"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.checkIsReminderDatabase("token", "db123"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void checkIsReminderDatabase_ShouldReturnFalse() {
        Database db = new Database("db123", Map.of("리마", new DatabaseProperty("1", "리마", "date")));

        when(notionDatabaseClient.fetchDatabase("token", "db123"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.checkIsReminderDatabase("token", "db123"))
                .expectNext(false)
                .verifyComplete();
    }
}
