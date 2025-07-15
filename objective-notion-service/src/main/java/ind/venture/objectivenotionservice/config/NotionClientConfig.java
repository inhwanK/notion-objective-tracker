package ind.venture.objectivenotionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class NotionClientConfig {

    @Value("${notion.api.key}")
    private String notionApiKey;

    @Bean
    public WebClient notionClient() {
        return WebClient.builder()
                .baseUrl("https://api.notion.com/v1/")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Notion-Version", "2022-06-28")
                .defaultHeader("Authorization", "Bearer " + notionApiKey)
                .filter((exceptionFilter()))
                .build();
    }

    private ExchangeFilterFunction exceptionFilter() {
        return ((request, next) ->
                next.exchange(request)
                        .flatMap(response -> {
                            if (response.statusCode().value() == 429) {
                                return Mono.error(new RuntimeException("Too Many Requests"));
                            }
                            return Mono.just(response);
                        })
        );
    }
}
