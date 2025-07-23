package ind.venture.objectivenotion.model.page;


import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.objectivenotion.model.page.property.PageProperty;

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

    private Parent parent;

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
        this.url = url;
        this.publicUrl = publicUrl;
        this.archived = archived;
        this.properties = properties;
        this.requestId = requestId;
    }

    public Page(
            String id,
            String createdTime,
            String lastEditedTime,
            boolean archived,
            String url,
            String publicUrl,
            Map<String, PageProperty> properties,
            String requestId,
            Parent parent
    ) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.url = url;
        this.publicUrl = publicUrl;
        this.archived = archived;
        this.properties = properties;
        this.requestId = requestId;
        this.parent = parent;
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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
                ", parent=" + parent +
                '}';
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Parent {
        private String type;         // "database_id" 또는 "page_id" 등
        private String database_id;  // 데이터베이스에 속한 경우
        private String page_id;      // 페이지에 속한 경우

        public Parent() {
        }

        public Parent(String type, String database_id, String page_id) {
            this.type = type;
            this.database_id = database_id;
            this.page_id = page_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDatabase_id() {
            return database_id;
        }

        public void setDatabase_id(String database_id) {
            this.database_id = database_id;
        }

        public String getPage_id() {
            return page_id;
        }

        public void setPage_id(String page_id) {
            this.page_id = page_id;
        }

        @Override
        public String toString() {
            return "Parent{" +
                    "type='" + type + '\'' +
                    ", database_id='" + database_id + '\'' +
                    ", page_id='" + page_id + '\'' +
                    '}';
        }
    }
}
