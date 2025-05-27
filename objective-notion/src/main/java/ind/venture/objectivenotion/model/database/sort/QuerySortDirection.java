package ind.venture.objectivenotion.model.database.sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuerySortDirection {
    @JsonProperty("ascending")
    ASCENDING,

    @JsonProperty("descending")
    DESCENDING
}
