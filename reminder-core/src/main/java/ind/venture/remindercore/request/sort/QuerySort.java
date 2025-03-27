package ind.venture.remindercore.request.sort;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuerySort {

    private String property;
    private String direction;

    public QuerySort() {
    }

    public QuerySort(String property, String direction) {
        this.property = property;
        this.direction = direction != null ? direction : "ascending";
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
