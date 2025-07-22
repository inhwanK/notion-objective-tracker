package ind.venture.objectivenotionservice.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenAiClientConfig {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public WebClient openAiClient() {
        return WebClient.builder()
                .baseUrl("https://api.openai.com/v1/responses")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + openAiApiKey)
                .build();
    }
}
