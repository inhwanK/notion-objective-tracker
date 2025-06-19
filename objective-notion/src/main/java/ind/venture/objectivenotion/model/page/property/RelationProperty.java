package ind.venture.objectivenotion.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import ind.venture.objectivenotion.model.page.type.Relation;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
public class RelationProperty extends PageProperty {
    private List<Relation> relation;

    @JsonProperty("has_more")
    private Boolean hasMore;

    public RelationProperty() {
        super();
    }

    public RelationProperty(String id, List<Relation> relation, Boolean hasMore) {
        super(id, "relation");
        this.relation = relation;
        this.hasMore = hasMore;
    }

    public List<Relation> getRelation() {
        return relation;
    }

    public Boolean getHasMore() {
        return hasMore;
    }
}
