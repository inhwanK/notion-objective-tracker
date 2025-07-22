package ind.venture.objectivenotionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIPromptRequest {

    private String model;
    private List<Input> input;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        private String role;
        private List<Content> content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private String type;
        private String text;
    }


    public static OpenAIPromptRequest of(String model, String systemPrompt, String userPrompt) {
        Content systemContent = new Content("input_text", systemPrompt);
        Input systemInput = new Input("system", List.of(systemContent));

        Content userContent = new Content("input_text", userPrompt);
        Input userInput = new Input("user", List.of(userContent));

        return new OpenAIPromptRequest(model, List.of(systemInput, userInput));
    }
}
