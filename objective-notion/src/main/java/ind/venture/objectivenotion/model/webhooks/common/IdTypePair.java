package ind.venture.objectivenotion.model.webhooks.common;

public class IdTypePair {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "IdTypePair{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
