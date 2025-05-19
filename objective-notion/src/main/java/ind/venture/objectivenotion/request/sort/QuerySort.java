package ind.venture.objectivenotion.request.sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuerySort {
    private final String property;
    private final Direction direction;

    public enum Direction {
        @JsonProperty("ascending")
        ASCENDING,

        @JsonProperty("descending")
        DESCENDING
    }

    public QuerySort(String property) {
        this(property, Direction.ASCENDING);
    }

    public QuerySort(String property, Direction direction) {
        if (property == null || property.isBlank()) {
            throw new IllegalArgumentException("property 필드가 존재하지 않습니다.");
        }
        this.property = property;
        this.direction = direction != null ? direction : Direction.ASCENDING;
    }

    public String getProperty() {
        return property;
    }

    public String getDirection() {
        return direction.name().toLowerCase();
    }

    public Direction getDirectionEnum() {
        return direction;
    }
}
