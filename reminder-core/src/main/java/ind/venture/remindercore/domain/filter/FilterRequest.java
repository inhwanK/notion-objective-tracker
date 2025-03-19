package ind.venture.remindercore.domain.filter;

public class FilterRequest {
    private PropertyFilter filter;

    public FilterRequest() {}

    public FilterRequest(PropertyFilter filter) {
        this.filter = filter;
    }

    public PropertyFilter getFilter() {
        return filter;
    }
}
