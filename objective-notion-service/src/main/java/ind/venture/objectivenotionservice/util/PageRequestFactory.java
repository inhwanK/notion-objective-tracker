package ind.venture.objectivenotionservice.util;

import ind.venture.objectivenotion.model.page.type.Link;
import ind.venture.objectivenotion.model.page.type.Text;
import ind.venture.objectivenotion.request.CreatePageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRequestFactory {
    public static CreatePageRequest createSubPageRequest(
            String databaseId,
            String title,
            String parentPageId
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("목표", createTitleProperty(title));
        properties.put("상위 항목", createRelationProperty(parentPageId));

        Map<String, Object> parent = Map.of("database_id", databaseId);
        return new CreatePageRequest(parent, properties);
    }

    public static Map<String, Object> createTitleProperty(String title) {
        return Map.of(
                "title", List.of(
                        Map.of("text", Map.of("content", title))
                )
        );
    }

    public static Map<String, Object> createRelationProperty(String parentPageId) {
        return Map.of(
                "relation", List.of(
                        Map.of("id", parentPageId)
                )
        );
    }

    public static Text createText(String content, String href) {
        return new Text(content, href != null ? new Link(href) : null);
    }
}
