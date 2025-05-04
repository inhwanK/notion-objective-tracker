package ind.venture.remindercore.servic;

import ind.venture.remindercore.config.NotionWebClient;
import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.property.DatabaseProperty;
import ind.venture.remindercore.service.NotionDatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

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
    void checkIsReminderDatabase_ShouldReturnTrue() {
        Database db = new Database("db123", Map.of("리마인더", new DatabaseProperty("1", "리마인더", "date")));

        when(notionWebClient.fetchDatabase("token", "db123"))
                .thenReturn(Mono.just(db));

        StepVerifier.create(notionDatabaseService.checkIsReminderDatabase("token", "db123"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void getDatabaseInfo_Success() {
        Assertions.fail("미구현");
    }

    @Test
    void getDatabaseInfo_Exception() {
        Assertions.fail("미구현");
    }

    @Test
    void findReminderPageSuccess() {
        Assertions.fail("미구현");
    }

    @Test
    void findReminderPageException() {
        Assertions.fail("미구현");
    }
}
