package ind.venture.objectivenotionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIPromptRequest {

    private Prompt prompt;
    private Map<String, Object> inputs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prompt {
        private String id;
        private int version;
    }

    public static OpenAIPromptRequest of(String promptId, int version, Map<String, Object> inputs) {
        return new OpenAIPromptRequest(new Prompt(promptId, version), inputs);
    }

    public static OpenAIPromptRequest of(String promptId, int version, String goal) {
        return of(promptId, version, Map.of("goal", goal));
    }
}
