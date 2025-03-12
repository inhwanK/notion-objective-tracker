package ind.venture.remindercore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Database {

    private String id;
    private String url;
    @JsonProperty("public_url")
    private String publicUrl;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("last_edited_time")
    private String lastEditedTime;
    @JsonProperty("is_inline")
    private boolean inline;

    @JsonProperty("archived")
    private boolean archived;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("in_trash")
    private boolean inTrash;
    private Map<String, DatabaseProperty> properties;

    public Database() {
    }

    public Database(String id, Map<String, DatabaseProperty> properties) {
        this.id = id;
        this.properties = properties;
    }

    public Database(
            String id, String url,
            String publicUrl,
            String createdTime,
            String lastEditedTime,
            boolean inline,
            boolean archived,
            String requestId,
            boolean inTrash,
            Map<String, DatabaseProperty> properties
    ) {
        this.id = id;
        this.url = url;
        this.publicUrl = publicUrl;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.inline = inline;
        this.archived = archived;
        this.requestId = requestId;
        this.inTrash = inTrash;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLastEditedTime() {
        return lastEditedTime;
    }

    public boolean isInline() {
        return inline;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getRequestId() {
        return requestId;
    }

    public boolean isInTrash() {
        return inTrash;
    }

    public Map<String, DatabaseProperty> getProperties() {
        return properties;
    }
}
