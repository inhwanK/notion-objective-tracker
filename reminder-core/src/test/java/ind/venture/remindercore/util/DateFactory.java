package ind.venture.remindercore.util;

import ind.venture.remindercore.domain.property.Date;

import java.time.LocalDate;

public class DateFactory {
    public static Date daysFromNowAsIsoDate(int days) {
        return new Date(LocalDate.now().plusDays(days).toString(), null, null);
    }

    public static Date daysAgoAsIsoDate(int days) {
        return new Date(LocalDate.now().minusDays(days).toString(), null, null);
    }

    public static Date fixedDate(int year, int month, int day) {
        return new Date(LocalDate.of(year, month, day).toString(), null, null);
    }

}
