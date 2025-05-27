package ind.venture.objectivenotion.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import ind.venture.objectivenotion.model.database.filter.QueryTopLevelFilter;
import ind.venture.objectivenotion.model.database.sort.QuerySort;
import ind.venture.objectivenotion.request.common.Pagination;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryDatabaseRequest implements Pagination {

    private QueryTopLevelFilter filter;
    private List<QuerySort> sorts;
    private String startCursor;
    private Integer pageSize;

    public QueryDatabaseRequest() {
    }

    public QueryDatabaseRequest(QueryTopLevelFilter filter, List<QuerySort> sorts) {
        this(filter, sorts, null, null);
    }

    public QueryDatabaseRequest(QueryTopLevelFilter filter, List<QuerySort> sorts, String startCursor, Integer pageSize) {
        this.filter = filter;
        this.sorts = sorts;
        this.startCursor = startCursor;
        this.pageSize = pageSize;
    }

    public QueryTopLevelFilter getFilter() {
        return filter;
    }

    public List<QuerySort> getSorts() {
        return sorts;
    }

    @Override
    public String getStartCursor() {
        return startCursor;
    }


    @Override
    public Integer getPageSize() {
        return pageSize;
    }

}
