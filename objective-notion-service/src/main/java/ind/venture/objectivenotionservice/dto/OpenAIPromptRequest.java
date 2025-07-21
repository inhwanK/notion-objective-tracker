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

    private Prompt prompt;
    private List<Input> input;
    private Map<String, Object> reasoning;
    private Integer max_output_tokens;
    private Boolean store;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prompt {
        private String id;
        private int version;
    }

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

    public static OpenAIPromptRequest of(String promptId, int version, String inputText) {
        Content content = new Content("input_text", inputText);
        Input input = new Input("user", List.of(content));
        return new OpenAIPromptRequest(
                new Prompt(promptId, version),
                List.of(input),
                Map.of(),
                2048,
                true
        );
    }
}
