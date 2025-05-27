package ind.venture.objectivenotion.model.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import ind.venture.objectivenotion.model.database.filter.condition.DateFilter;

public class PropertyFilter implements QueryTopLevelFilter, CompoundFilterElement {
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
