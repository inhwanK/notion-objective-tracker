package ind.venture.remindercore.domain.query;

import ind.venture.remindercore.domain.Page;

import java.util.List;

public class QueryResults {
    private String type;
    private List<Page> results;

    public QueryResults() {}
    public QueryResults(String type, List<Page> results) {
        this.type = type;
        this.results = results;
    }

    public String getType() {
        return type;
    }

    public List<Page> getResults() {
        return results;
    }


}
