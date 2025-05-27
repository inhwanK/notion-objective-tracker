package ind.venture.objectivenotion.model.database;

import ind.venture.objectivenotion.model.common.Pagination;
import ind.venture.objectivenotion.model.page.Page;

import java.util.List;

public class QueryResults implements Pagination {
    private final String type;
    private final List<Page> results;
    private final String nextCursor;
    private final Boolean hasMore;

    public QueryResults(String type, List<Page> results, String nextCursor, Boolean hasMore) {
        this.type = type;
        this.results = results;
        this.nextCursor = nextCursor;
        this.hasMore = hasMore;
    }

    public String getType() {
        return type;
    }

    public List<Page> getResults() {
        return results;
    }

    @Override
    public String getNextCursor() {
        return nextCursor;
    }

    @Override
    public Boolean getHasMore() {
        return hasMore;
    }
}
