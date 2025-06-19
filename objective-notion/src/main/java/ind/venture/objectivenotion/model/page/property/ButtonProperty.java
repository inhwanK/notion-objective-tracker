package ind.venture.objectivenotion.model.page.property;

import lombok.ToString;

@ToString(callSuper = true)
public class ButtonProperty extends PageProperty {
    private String button;

    public ButtonProperty() {
        super();
    }

    public ButtonProperty(String id, String type, String button) {
        super(id, type);
        this.button = button;
    }

}
