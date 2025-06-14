package ind.venture.objectivenotion.model.page.property;

public class UrlProperty extends PageProperty {
    private String url;

    public UrlProperty() {
        super();
    }

    public UrlProperty(String id, String url) {
        super(id, "url");
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
