package ind.venture.remindercore.domain;

import java.util.Map;

public class Page {
    private String id;
    private String url;
    private String publicUrl;
    private Map<String, DatabaseProperty> properties;

    public Page() {
    }

    public Page(String id, String url, String publicUrl, Map<String, DatabaseProperty> properties) {
        this.id = id;
        this.url = url;
        this.publicUrl = publicUrl;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public Map<String, DatabaseProperty> getProperties() {
        return properties;
    }
}
