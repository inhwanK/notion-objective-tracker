package ind.venture.objectivenotion.model.page.property;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;

public class EmailProperty extends PageProperty {
    private String email;

    public EmailProperty() {
        super();
    }

    public EmailProperty(String id, String email) {
        super(id, "email");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
