package ind.venture.objectivenotion.model.page.property;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = UnknownPageProperty.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TitleProperty.class, name = "title"),
        @JsonSubTypes.Type(value = DateProperty.class, name = "date"),
        @JsonSubTypes.Type(value = RelationProperty.class, name = "relation"),
        @JsonSubTypes.Type(value = UrlProperty.class, name = "url"),
        @JsonSubTypes.Type(value = EmailProperty.class, name = "email"),
        @JsonSubTypes.Type(value = CreatedTimeProperty.class, name = "created_time"),
        @JsonSubTypes.Type(value = LastEditedTimeProperty.class, name = "last_edited_time"),
        @JsonSubTypes.Type(value = ButtonProperty.class, name = "button")
})
public abstract class PageProperty {
    private String id;
    private String type;

    public PageProperty() {
    }

    public PageProperty(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "PageProperty{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
