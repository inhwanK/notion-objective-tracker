package ind.venture.remindercore.domain;

public class PageProperty {
    private String id;
    private String type;

    public PageProperty() {}
    public PageProperty(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
