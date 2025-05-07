package ind.venture.remindercore.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.remindercore.domain.property.PageProperty;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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

    @Override
    public String toString() {
        return "Page{" +
                "id='" + id + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", lastEditedTime='" + lastEditedTime + '\'' +
                ", archived=" + archived +
                ", url='" + url + '\'' +
                ", publicUrl='" + publicUrl + '\'' +
                ", properties=" + properties +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
