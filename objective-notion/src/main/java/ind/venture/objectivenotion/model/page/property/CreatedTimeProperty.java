package ind.venture.objectivenotion.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedTimeProperty extends PageProperty {
    @JsonProperty("created_time")
    private String createdTime;

    public CreatedTimeProperty() {
        super();
    }

    public CreatedTimeProperty(String id, String type, String createdTime) {
        super(id, "created_time");
        this.createdTime = createdTime;
    }
}
