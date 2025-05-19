package ind.venture.objectivenotion.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyFilter implements QueryFilter {
    private static final String property = "리마인더";
    @JsonProperty("date")
    private DateFilter dateFilter;

    public PropertyFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    public DateFilter getDateFilter() {
        return dateFilter;
    }

    public String getProperty() {
        return property;
    }

    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }
}
