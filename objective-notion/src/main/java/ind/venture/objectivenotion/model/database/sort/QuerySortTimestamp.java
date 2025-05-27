package ind.venture.objectivenotion.model.database.sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuerySortTimestamp {
    @JsonProperty("created_time")
    CREATED_TIME,

    @JsonProperty("last_edited_time")
    LAST_EDITED_TIME

}
