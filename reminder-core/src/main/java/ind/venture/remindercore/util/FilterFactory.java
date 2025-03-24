package ind.venture.remindercore.util;

import ind.venture.remindercore.domain.filter.CompoundFilter;
import ind.venture.remindercore.domain.filter.DateFilter;
import ind.venture.remindercore.domain.filter.FilterRequest;
import ind.venture.remindercore.domain.filter.PropertyFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FilterFactory {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static FilterRequest createNotEmptyFilterRequest() {

        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder().notEmpty(true).build()
        );
        return new FilterRequest(propertyFilter);
    }

    public static FilterRequest createTodayFilterRequest() {

        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder().equals(LocalDate.now().format(DATE_FORMATTER)).build()
        );
        return new FilterRequest(propertyFilter);
    }

    public static FilterRequest createWeeklyFilterRequest() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);
        return createDateRangeFilterRequest(today, oneWeekLater);
    }

    public static FilterRequest createDateRangeFilterRequest(LocalDate start, LocalDate end) {
        PropertyFilter startFilter = new PropertyFilter(
                DateFilter.builder().onOrAfter(start.format(DATE_FORMATTER)).build()
        );
        PropertyFilter endFilter = new PropertyFilter(
                DateFilter.builder().onOrBefore(end.format(DATE_FORMATTER)).build()
        );
        return new FilterRequest(CompoundFilter.and(List.of(startFilter, endFilter)));
    }
}
