package ind.venture.objectivenotion.model.database.sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuerySort {
    private final String property;
    private final QuerySortDirection direction;

    public QuerySort(String property) {
        this(property, QuerySortDirection.ASCENDING);
    }

    public QuerySort(String property, QuerySortDirection direction) {
        this.property = property;
        this.direction = direction != null ? direction : QuerySortDirection.ASCENDING;
    }

    public String getProperty() {
        return property;
    }

    public String getDirection() {
        return direction.name().toLowerCase();
    }

    public QuerySortDirection getDirectionEnum() {
        return direction;
    }
}
