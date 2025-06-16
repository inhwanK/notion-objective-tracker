package ind.venture.objectivenotion.model.page.type;

public class Relation {
    private String id;

    public Relation() {
    }

    public Relation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RelationItem{" +
                "id='" + id + '\'' +
                '}';
    }
}
