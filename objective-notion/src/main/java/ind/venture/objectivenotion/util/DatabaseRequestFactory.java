package ind.venture.objectivenotion.util;

import ind.venture.objectivenotion.request.DatabaseRequest;
import ind.venture.objectivenotion.model.database.filter.CompoundFilter;
import ind.venture.objectivenotion.model.database.filter.condition.DateFilter;
import ind.venture.objectivenotion.model.database.filter.PropertyFilter;
import ind.venture.objectivenotion.model.database.sort.QuerySort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatabaseRequestFactory {

    private static final String REMINDER_PROPERTY = "리마인더";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DatabaseRequest createNotEmptyReminderRequest() {
        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder()
                        .notEmpty(true)
                        .build()
        );
        return new DatabaseRequest(propertyFilter, List.of(createDefaultSortByReminder()));
    }

    public static DatabaseRequest createTodayReminderRequest() {
        PropertyFilter propertyFilter = new PropertyFilter(
                DateFilter.builder()
                        .equals(formatDate(LocalDate.now()))
                        .build()
        );
        return new DatabaseRequest(propertyFilter, List.of(createDefaultSortByReminder()));
    }

    public static DatabaseRequest createWeeklyReminderRequest() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);
        return createDateRangeReminderRequest(today, oneWeekLater);
    }

    public static DatabaseRequest createDateRangeReminderRequest(LocalDate start, LocalDate end) {
        PropertyFilter startFilter = new PropertyFilter(
                DateFilter.builder().onOrAfter(formatDate(start)).build()
        );
        PropertyFilter endFilter = new PropertyFilter(
                DateFilter.builder().onOrBefore(formatDate(end)).build()
        );

        CompoundFilter dateRangeFilter = CompoundFilter.and(List.of(startFilter, endFilter));

        return new DatabaseRequest(dateRangeFilter, List.of(createDefaultSortByReminder()));
    }

    private static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    private static QuerySort createDefaultSortByReminder() {
        return new QuerySort(REMINDER_PROPERTY); // 기본 정렬: ascending
    }
}
