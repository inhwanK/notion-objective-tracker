package ind.venture.remindercore.domain;


import ind.venture.remindercore.domain.property.DatabaseProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DatabaseTests {


    @Test
    @DisplayName("데이터베이스에 날짜 속성이 존재하고, 속성 이름이 '리마인더'이면 true를 반환한다. ")
    void isReminderTrue() {
        HashMap<String, DatabaseProperty> properties = new HashMap<>();
        DatabaseProperty reminder = new DatabaseProperty("asdfas", "리마인더", "date");
        properties.put("리마인더", reminder);

        Database db = new Database("abcd", properties);
        Assertions.fail("다시 구현");
    }

    @Test
    @DisplayName("데이터베이스에 날짜 속성이 존재하지만, 속성 이름이 '리마인더'가 아니면 false를 반환한다. ")
    void isReminderFalseBecausePropertyExistButNotReminder() {
        HashMap<String, DatabaseProperty> properties = new HashMap<>();
        DatabaseProperty reminder = new DatabaseProperty("asdfas", "날짜", "date");
        properties.put("날짜", reminder);

        Database db = new Database("abcd", properties);
        Assertions.fail("다시 구현");
    }

    @Test
    @DisplayName("데이터베이스에 날짜 속성이 존재하지 않으면, false를 반환한다. ")
    void isReminderFalseBecausePropertyNotExist() {
        HashMap<String, DatabaseProperty> properties = new HashMap<>();

        Database db = new Database("abcd", properties);
        Assertions.fail("다시 구현");
    }


}
