package ind.venture.remindercore.request.validate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class DatabaseValidateTests {

    @Autowired
    private WebClient webClient;

    @Value("${notion.api.key}")
    private String notionApiKey;

    @Value("${notion.database.url}")
    private String notionDatabaseUrl;
}
