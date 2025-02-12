package ind.venture.remindercore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;

    public Property() {
    }

    public Property(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isReminderProperty() {
        if (type.equals("date") && name.equals("리마인더")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
