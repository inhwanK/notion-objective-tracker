package ind.venture.objectivenotion.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastEditedTimeProperty extends PageProperty {
    @JsonProperty("last_edited_time")
    private String lastEditedTime;

    public LastEditedTimeProperty() {
    }

    public LastEditedTimeProperty(String id, String type, String lastEditedTime) {
        super(id, "last_edited_time");
        this.lastEditedTime = lastEditedTime;
    }
}
