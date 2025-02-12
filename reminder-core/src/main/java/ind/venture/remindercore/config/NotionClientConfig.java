package ind.venture.remindercore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NotionClientConfig {


    @Bean
    public WebClient notionClient() {
        return WebClient.builder()
                .baseUrl("https://api.notion.com/v1/")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Notion-Version", "2022-06-28")
                .build();
    }
}
