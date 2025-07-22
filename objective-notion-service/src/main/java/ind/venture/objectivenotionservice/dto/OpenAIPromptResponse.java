package ind.venture.objectivenotionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIPromptResponse {
    private String id;
    private String object;
    private List<Output> output;

    @Data
    public static class Output {
        private String id;
        private String type;
        private String status;
        private List<Content> content;
    }

    @Data
    public static class Content {
        private String type;
        private String text;
    }
}