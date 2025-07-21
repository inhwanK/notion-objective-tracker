package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotionservice.dto.OpenAIPromptRequest;
import ind.venture.objectivenotionservice.dto.OpenAIPromptResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OpenAiWebClient implements OpenAiObjectivePromptClient {
    private final WebClient openAiClient;

    @Value("${spring.ai.openai.prompt.id}")
    private String promptId;

    @Value("${spring.ai.openai.prompt.version}")
    private int promptVersion;

    public OpenAiWebClient(WebClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    @Override
    public Mono<String> getObjectivesFromPromptId(String goal) {
        OpenAIPromptRequest request = OpenAIPromptRequest.of(promptId, promptVersion, goal);
        log.info("OpenAI Prompt request: {}", request);
        return openAiClient
                .post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAIPromptResponse.class)
                .map(this::extractContentFromResponse);
    }

    private String extractContentFromResponse(OpenAIPromptResponse response) {
        if (response == null || response.getOutput() == null || response.getOutput().isEmpty())
            return "";
        OpenAIPromptResponse.Output output = response.getOutput().get(0);
        if (output.getContent() == null || output.getContent().isEmpty())
            return "";
        return output.getContent().get(0).getText();
    }
}
