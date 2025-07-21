package ind.venture.objectivenotionservice.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OpenAIPromptResponse {
    private String id;
    private String object;
    private long created_at;
    private String status;
    private String error;
    private String model;
    private List<Output> output;
    private Usage usage;
    private Double temperature;
    private Double top_p;
    private Boolean parallel_tool_calls;
    private String previous_response_id;
    private Map<String, Object> metadata;

    @Data
    public static class Output {
        private String type;
        private String id;
        private String status;
        private String role;
        private List<Content> content;
    }

    @Data
    public static class Content {
        private String type;
        private String text;
        private List<Object> annotations; // 어노테이션이 배열로 오므로 List로 받음
    }

    @Data
    public static class Usage {
        private Integer input_tokens;
        private InputTokensDetails input_tokens_details;
        private Integer output_tokens;
        private OutputTokensDetails output_tokens_details;
        private Integer total_tokens;
    }

    @Data
    public static class InputTokensDetails {
        private Integer cached_tokens;
    }

    @Data
    public static class OutputTokensDetails {
        private Integer reasoning_tokens;
    }
}