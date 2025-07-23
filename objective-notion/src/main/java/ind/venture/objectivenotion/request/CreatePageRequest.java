package ind.venture.objectivenotion.request;

import ind.venture.objectivenotion.model.page.property.PageProperty;

import java.util.Map;

public class CreatePageRequest {
    private Map<String, Object> parent;
    private Map<String, Object> properties;

    public CreatePageRequest(Map<String, Object> parent, Map<String, Object> properties) {
        this.parent = parent;
        this.properties = properties;
    }

    public Map<String, Object> getParent() { return parent; }
    public Map<String, Object> getProperties() { return properties; }
}
