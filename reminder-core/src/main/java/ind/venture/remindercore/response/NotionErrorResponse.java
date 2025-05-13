package ind.venture.remindercore.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionErrorResponse {

    private String object;
    private int status;
    private String code;
    private String message;

    @JsonProperty("request_id")
    private String requestId;

    public String getObject() {
        return object;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "NotionErrorResponse{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
