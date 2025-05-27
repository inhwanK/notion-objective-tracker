package ind.venture.objectivenotion.model.page;


import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.objectivenotion.model.page.property.PageProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {

    private String id;
    private String createdTime;
    private String lastEditedTime;
    private String url;
    private String publicUrl;
    private boolean archived;
    private Map<String, PageProperty> properties;
    private String requestId;
    private boolean inTrash;

    public Page() {
    }

    public Page(
            String id,
            String createdTime,
            String lastEditedTime,
            String url,
            String publicUrl,
            boolean archived,
            Map<String, PageProperty> properties,
            String requestId,
            boolean inTrash
    ) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.url = url;
        this.publicUrl = publicUrl;
        this.archived = archived;
        this.properties = properties;
        this.requestId = requestId;
        this.inTrash = inTrash;
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

    public String getUrl() {
        return url;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public boolean isArchived() {
        return archived;
    }

    public Map<String, PageProperty> getProperties() {
        return properties;
    }

    public String getRequestId() {
        return requestId;
    }

    public boolean isInTrash() {
        return inTrash;
    }
}
