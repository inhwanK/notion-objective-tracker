package ind.venture.remindercore.domain.filter;

public class FilterRequest {

    private QueryFilter filter;

    public FilterRequest() {
    }

    public FilterRequest(QueryFilter filter) {
        this.filter = filter;
    }

    public QueryFilter getFilter() {
        return filter;
    }
}
