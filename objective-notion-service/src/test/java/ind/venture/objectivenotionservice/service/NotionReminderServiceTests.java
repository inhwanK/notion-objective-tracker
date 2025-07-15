package ind.venture.objectivenotionservice.service;


import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.database.DatabaseProperty;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionservice.client.NotionWebClient;
import ind.venture.objectivenotionservice.util.DateFactory;
import ind.venture.objectivenotionservice.util.PageFactory;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
public class NotionReminderServiceTests {

    @InjectMocks
    private NotionReminderService notionReminderService;

    @Mock
    private NotionWebClient notionWebClient;

    @BeforeEach
    public void setUp() {
        notionReminderService = new NotionReminderService(notionWebClient);
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
        when(notionWebClient.queryDatabase(any(), any())) // TODO DatabaseRequest 필요
                .thenReturn(Mono.just(List.of(pages.get(0), pages.get(2))));

        Mono<List<Page>> result = notionReminderService.findAllReminderPage("test-db");

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
        when(notionWebClient.queryDatabase(eq("db-id"), any()))
                .thenReturn(Mono.error(badRequestException));

        // then
        StepVerifier.create(notionReminderService.findAllReminderPage("db-id"))
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException ex &&
                                ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                .verify();
    }


    @Test
    void findTodayReminderPage_ShouldReturnPage() {
        // given
        Page page1 = PageFactory.createBasePage("page-1", "Today Reminder", false, "req-1");
        page1.getProperties().put("리마인더", PageFactory.createDateProperty("reminder-1", DateFactory.todayAsIsoDate()));

        Page page2 = PageFactory.createBasePage("page-2", "Past Reminder", false, "req-2");
        page2.getProperties().put("리마인더", PageFactory.createDateProperty("reminder-2", DateFactory.todayAsIsoDate()));

        List<Page> allPages = List.of(page1, page2);

        // TODO DatabaseRequest 필요
        when(notionWebClient.queryDatabase(any(), any()))
                .thenReturn(Mono.just(allPages));

        // when
        Mono<List<Page>> result = notionReminderService.findTodayReminderPage("db-id");

        // then
        StepVerifier.create(result)
                .expectNextMatches(pages ->
                        pages.size() == 2 && pages.containsAll(List.of(page1, page2))
                )
                .verifyComplete();
    }

    @Test
    void findTodayReminderPage_400Error() {
        WebClientResponseException badRequestException = WebClientResponseException.create(
                400, "Bad Request", null, null, null);

        when(notionWebClient.queryDatabase(eq("db-id"), any()))
                .thenReturn(Mono.error(badRequestException));

        StepVerifier.create(notionReminderService.findTodayReminderPage("db-id"))
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException ex &&
                                ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                .verify();
    }

    @Test
    void findWeeklyReminderPage_ShouldReturnPage() {
        // given
        Page page1 = PageFactory.createBasePage("page1", "Weekly Reminder", false, "req-1");
        page1.getProperties().put("리마인더", PageFactory.createDateProperty("reminder", DateFactory.daysFromNowAsIsoDate(8)));

        Page page2 = PageFactory.createBasePage("page2", "Weekly Reminder", false, "req-2");
        page2.getProperties().put("리마인더", PageFactory.createDateProperty("reminder", DateFactory.daysFromNowAsIsoDate(7)));

        when(notionWebClient.queryDatabase(eq("db-id"), any()))
                .thenReturn(Mono.just(List.of(page2)));

        // when
        // TODO DatabaseRequest 필요
        Mono<List<Page>> result = notionReminderService.findWeeklyReminderPage("db-id");

        // then
        StepVerifier.create(result)
                .expectNextMatches(pages ->
                        pages.size() == 1 &&
                                pages.contains(page2))
                .verifyComplete();
    }

    @Test
    void findWeeklyReminderPage_400Error() {
        WebClientResponseException badRequestException = WebClientResponseException.create(
                400, "Bad Request", null, null, null);

        when(notionWebClient.queryDatabase(eq("db-id"), any()))
                .thenReturn(Mono.error(badRequestException));

        StepVerifier.create(notionReminderService.findWeeklyReminderPage("db-id"))
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException ex &&
                                ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                .verify();
    }
}
