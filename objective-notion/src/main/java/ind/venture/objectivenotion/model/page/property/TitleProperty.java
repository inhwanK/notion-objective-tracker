package ind.venture.objectivenotion.model.page.property;

import ind.venture.objectivenotion.model.page.type.RichText;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
public class TitleProperty extends PageProperty {
    private List<RichText> title;

    public TitleProperty() {
        super();
    }

    public TitleProperty(String id, List<RichText> title) {
        super(id, "title");
        this.title = title;
    }

    public List<RichText> getTitle() {
        return title;
    }
}
