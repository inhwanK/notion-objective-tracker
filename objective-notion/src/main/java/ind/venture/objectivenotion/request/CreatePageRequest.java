package ind.venture.objectivenotion.request;

import ind.venture.objectivenotion.model.page.property.PageProperty;

import java.util.Map;

public class CreatePageRequest {
    private Parent parent;
    private Map<String, PageProperty> properties;

    public static class Parent {
        private String database_id;

        public Parent(String databaseId) {
            this.database_id = databaseId;
        }
        public String getDatabase_id() { return database_id; }
        public void setDatabase_id(String database_id) { this.database_id = database_id; }
    }

    public CreatePageRequest(Parent parent, Map<String, PageProperty> properties) {
        this.parent = parent;
        this.properties = properties;
    }

    public Parent getParent() { return parent; }
    public Map<String, PageProperty> getProperties() { return properties; }
}
