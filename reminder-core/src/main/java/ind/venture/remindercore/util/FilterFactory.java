package ind.venture.remindercore.util;

import ind.venture.remindercore.domain.filter.DateFilter;
import ind.venture.remindercore.domain.filter.FilterRequest;
import ind.venture.remindercore.domain.filter.PropertyFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FilterFactory {
    private static final String REMINDER_PROPERTY_NAME = "리마인더";

    public static FilterRequest createNotEmptyFilterRequest() {

        DateFilter dateFilter = DateFilter.builder()
                .notEmpty(true)
                .build();

        PropertyFilter propertyFilter = new PropertyFilter(REMINDER_PROPERTY_NAME, dateFilter);
        return new FilterRequest(propertyFilter);
    }

    public static FilterRequest createTodayFilterRequest() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateFilter dateFilter = DateFilter.builder()
                .equals(today)
                .build();

        PropertyFilter propertyFilter = new PropertyFilter(REMINDER_PROPERTY_NAME, dateFilter);
        return new FilterRequest(propertyFilter);
    }
}
