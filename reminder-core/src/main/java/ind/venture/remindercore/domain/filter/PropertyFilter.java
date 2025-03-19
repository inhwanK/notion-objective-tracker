package ind.venture.remindercore.domain.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyFilter {
    private String property;
    @JsonProperty("date")
    private DateFilter dateFilter;

    public PropertyFilter(String property, DateFilter dateFilter) {
        this.property = property;
        this.dateFilter = dateFilter;
    }

    public DateFilter getDateFilter() {
        return dateFilter;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }
}
