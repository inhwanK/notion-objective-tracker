package ind.venture.objectivenotion.model.database;

import ind.venture.objectivenotion.model.page.property.RichText;

public class DatabaseProperty {

    private String id;
    private String name;
    private String type;
    private RichText title;
    private RichText richText;
//    private Select select;
//    private Status status;


    public DatabaseProperty() {
    }

    public DatabaseProperty(String id, String name, String type) {
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

    @Override
    public String toString() {
        return "DatabaseProperty{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
