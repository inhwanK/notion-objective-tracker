package ind.venture.objectivenotion.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.objectivenotion.domain.property.PageProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {
    private String id;
    private String createdTime;
    private String lastEditedTime;
    private boolean archived;
    private String url;
    private String publicUrl;
    private Map<String, PageProperty> properties;
    private String requestId;

    public Page() {
    }

    public Page(
            String id,
            String createdTime,
            String lastEditedTime,
            boolean archived,
            String url,
            String publicUrl,
            Map<String, PageProperty> properties,
            String requestId
    ) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.archived = archived;
        this.url = url;
        this.publicUrl = publicUrl;
        this.properties = properties;
        this.requestId = requestId;
    }

    public String getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLastEditedTime() {
        return lastEditedTime;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public Map<String, PageProperty> getProperties() {
        return properties;
    }

    public String getRequestId() {
        return requestId;
    }
}
