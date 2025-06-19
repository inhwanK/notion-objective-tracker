package ind.venture.objectivenotion.model.webhooks.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    /**
     * 공통
     */
    private Parent parent;

    /**
     * page.properties_updated
     */
    @JsonProperty("updated_properties")
    private List<String> updatedProperties;

    /**
     * database.schema_updated
     */
    private Map<String, Object> schema;

    /**
     * page.content_updated, database.content_updated
     */
    @JsonProperty("updated_by")
    private UpdatedBy updatedBy;

    /**
     * page.moved, database.moved
     */
    @JsonProperty("old_parent")
    private Parent oldParent;

    /**
     * page.created, database.created
     */
    @JsonProperty("title")
    private String title;

    /**
     * database.created 전용 - 아이콘
     */
    @JsonProperty("icon")
    private Object icon;

    /**
     * database.created 전용 - 커버
     */
    @JsonProperty("cover")
    private Object cover;

    /**
     * page.locked/unlocked 관련 (boolean flag)
     */
    @JsonProperty("is_locked")
    private Boolean isLocked;

    public Parent getParent() {
        return parent;
    }

    public List<String> getUpdatedProperties() {
        return updatedProperties;
    }

    public Map<String, Object> getSchema() {
        return schema;
    }

    public UpdatedBy getUpdatedBy() {
        return updatedBy;
    }

    public Parent getOldParent() {
        return oldParent;
    }

    public String getTitle() {
        return title;
    }

    public Object getIcon() {
        return icon;
    }

    public Object getCover() {
        return cover;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    @Override
    public String toString() {
        return "Data{" +
                "parent=" + parent +
                ", updatedProperties=" + updatedProperties +
                ", schema=" + schema +
                ", updatedBy=" + updatedBy +
                ", oldParent=" + oldParent +
                ", title='" + title + '\'' +
                ", icon=" + icon +
                ", cover=" + cover +
                ", isLocked=" + isLocked +
                '}';
    }
}