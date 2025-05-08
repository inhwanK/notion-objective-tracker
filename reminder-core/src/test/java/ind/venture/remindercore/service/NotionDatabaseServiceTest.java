package ind.venture.remindercore.service;

import ind.venture.remindercore.config.NotionWebClient;
import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.property.*;
import ind.venture.remindercore.util.DateFactory;
import ind.venture.remindercore.util.PageFactory;
import org.assertj.core.api.Assertions;
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

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
public class NotionDatabaseServiceTest {

    @InjectMocks
    private NotionDatabaseService notionDatabaseService;

    @Mock
    private NotionWebClient notionWebClient;

    @BeforeEach
    public void setUp() {
        notionDatabaseService = new NotionDatabaseService(notionWebClient);
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

        when(notionWebClient.fetchDatabase("token", "db-id"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.getDatabaseInfo("token", "db-id"))
                .expectNext(db)
                .verifyComplete();
    }

    @Test
    void getDatabaseInfo_400Error() {
        WebClientResponseException badRequestException = WebClientResponseException.create(
                400, "Bad Request", null, null, null);

        when(notionWebClient.fetchDatabase("token", "db-id"))
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

        when(notionWebClient.fetchDatabase("token", "db123"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.checkIsReminderDatabase("token", "db123"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void checkIsReminderDatabase_ShouldReturnFalse() {
        Database db = new Database("db123", Map.of("리마", new DatabaseProperty("1", "리마", "date")));

        when(notionWebClient.fetchDatabase("token", "db123"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.checkIsReminderDatabase("token", "db123"))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void findAllReminderPage_ShouldReturnPage() {
        // given
        List<Page> pages = List.of(
                PageFactory.createBasePage("page-1", "Test Page 1", false, "req-1"),
                PageFactory.createBasePage("page-2", "Test Page 2", false, "req-2"),
                PageFactory.createBasePage("page-3", "Test Page 3", false, "req-3"),
                PageFactory.createBasePage("page-4", "Test Page 4", false, "req-4"),
                PageFactory.createBasePage("page-5", "Test Page 5", false, "req-5")
        );

        pages.get(0).getProperties().put("리마인더",
                PageFactory.createDateProperty("reminder-1", DateFactory.daysFromNowAsIsoDate(2))
        );
        pages.get(2).getProperties().put("리마인더",
                PageFactory.createDateProperty("reminder-3", DateFactory.daysAgoAsIsoDate(1))
        );

        // when
        when(notionWebClient.queryDatabase(any(), any(), any()))
                .thenReturn(Mono.just(List.of(pages.get(0), pages.get(2))));

        Mono<List<Page>> result = notionDatabaseService.findAllReminderPage("test-token", "test-db");

        // then
        StepVerifier.create(result)
                .expectNextMatches(filtered ->
                        filtered.size() == 2 && filtered.containsAll(List.of(pages.get(0), pages.get(2)))
                )
                .verifyComplete();
    }

    @Test
    void findAllReminderPage_400Error() {
        // given
        WebClientResponseException badRequestException = WebClientResponseException.create(
                400, "Bad Request", null, null, null);

        // when
        when(notionWebClient.queryDatabase(eq("token"), eq("db-id"), any()))
                .thenReturn(Mono.error(badRequestException));

        // then
        StepVerifier.create(notionDatabaseService.findAllReminderPage("token", "db-id"))
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException ex &&
                                ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                .verify();
    }

    void findTodayReminderPage_ShouldReturnPage() {
        Assertions.fail("미구현");
    }

    void findTodayReminderPage_400Error() {
        Assertions.fail("미구현");
    }

    void findWeeklyReminderPage_ShouldReturnPage() {
        Assertions.fail("미구현");
    }

    void findWeeklyReminderPage_400Error() {
        Assertions.fail("미구현");
    }
}
