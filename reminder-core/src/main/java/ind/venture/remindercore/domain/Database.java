package ind.venture.remindercore.domain;

import java.util.Map;
import java.util.Optional;

public class Database {

    private String id;
    private Map<String, Property> properties;

    public Database() {
    }

    public Database(String id, Map<String, Property> properties) {
        this.id = id;
        this.properties = properties;
    }

    public boolean isReminder() {
        Optional<Property> reminder = Optional.ofNullable(properties.get("리마인더"));
        if(reminder.isEmpty()) {
            return false;
        }
        if(!reminder.get().isDate()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database{" +
                "id='" + id + '\'' +
                ", properties=" + properties +
                '}';
    }
}
