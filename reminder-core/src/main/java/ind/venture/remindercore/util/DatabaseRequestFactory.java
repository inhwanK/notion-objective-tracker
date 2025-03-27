package ind.venture.remindercore.util;

import ind.venture.remindercore.request.DatabaseRequest;
import ind.venture.remindercore.request.filter.CompoundFilter;
import ind.venture.remindercore.request.filter.DateFilter;
import ind.venture.remindercore.request.filter.PropertyFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatabaseRequestFactory {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DatabaseRequest createNotEmptyFilterRequest() {

        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder().notEmpty(true).build()
        );
        return new DatabaseRequest(propertyFilter);
    }

    public static DatabaseRequest createTodayFilterRequest() {

        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder().equals(LocalDate.now().format(DATE_FORMATTER)).build()
        );
        return new DatabaseRequest(propertyFilter);
    }

    public static DatabaseRequest createWeeklyFilterRequest() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);
        return createDateRangeFilterRequest(today, oneWeekLater);
    }

    public static DatabaseRequest createDateRangeFilterRequest(LocalDate start, LocalDate end) {
        PropertyFilter startFilter = new PropertyFilter(
                DateFilter.builder().onOrAfter(start.format(DATE_FORMATTER)).build()
        );
        PropertyFilter endFilter = new PropertyFilter(
                DateFilter.builder().onOrBefore(end.format(DATE_FORMATTER)).build()
        );
        return new DatabaseRequest(CompoundFilter.and(List.of(startFilter, endFilter)));
    }
}
