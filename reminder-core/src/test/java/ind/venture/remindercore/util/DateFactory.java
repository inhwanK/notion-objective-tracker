package ind.venture.remindercore.util;

import ind.venture.remindercore.domain.property.Date;

import java.time.LocalDate;

public class DateFactory {
    public static Date daysFromNowAsIsoDate(int days) {
        return simpleDate(LocalDate.now().plusDays(days).toString());
    }

    public static Date daysAgoAsIsoDate(int days) {
        return simpleDate(LocalDate.now().minusDays(days).toString());
    }

    public static Date fixedDate(int year, int month, int day) {
        return simpleDate(LocalDate.of(year, month, day).toString());
    }

    public static Date todayAsIsoDate() {
        return simpleDate(LocalDate.now().toString());
    }

    private static Date simpleDate(String isoDate) {
        return new Date(isoDate, null, null);
    }

}
