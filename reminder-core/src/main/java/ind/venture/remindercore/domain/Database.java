package ind.venture.remindercore.domain;

import java.util.Map;

public class Database {

    private String id;
    private String url;
    private Map<String, Property> properties;

    public Database() {
    }

    public Database(String id, String url, Map<String, Property> properties) {
        this.id = id;
        this.url = url;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public boolean isReminder() {
        for (Property property : getProperties().values()) {
            if (property.isReminderProperty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Database{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", properties=" + properties +
                '}';
    }
}
