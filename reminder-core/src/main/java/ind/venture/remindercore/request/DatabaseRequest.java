package ind.venture.remindercore.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.remindercore.request.filter.QueryFilter;
import ind.venture.remindercore.request.sort.QuerySort;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseRequest {

    private QueryFilter filter;
    private List<QuerySort> sorts;

    public DatabaseRequest() {
    }

    public DatabaseRequest(QueryFilter filter) {
        this.filter = filter;
    }

    public DatabaseRequest(List<QuerySort> sorts) {
        this.sorts = sorts;
    }

    public DatabaseRequest(QueryFilter filter, List<QuerySort> sorts) {
        this.filter = filter;
        this.sorts = sorts;
    }

    public QueryFilter getFilter() {
        return filter;
    }

    public void setFilter(QueryFilter filter) {
        this.filter = filter;
    }

    public List<QuerySort> getSorts() {
        return sorts;
    }

    public void setSorts(List<QuerySort> sorts) {
        this.sorts = sorts;
    }
}
