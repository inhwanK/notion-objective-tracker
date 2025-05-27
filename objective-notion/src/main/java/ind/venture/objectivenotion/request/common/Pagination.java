package ind.venture.objectivenotion.request.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Pagination {
    String getStartCursor();

    Integer getPageSize();

    default Map<String, List<String>> buildPaginationParams() {
        Map<String, List<String>> q = new HashMap<>();
        if (getStartCursor() != null) {
            q.put("start_cursor", Collections.singletonList(getStartCursor()));
        }
        if (getPageSize() != null) {
            q.put("page_size", Collections.singletonList(getPageSize().toString()));
        }
        return q;
    }
}
