package ind.venture.objectivenotionservice.util;

import ind.venture.objectivenotion.model.page.property.PageProperty;
import ind.venture.objectivenotion.model.page.property.RelationProperty;
import ind.venture.objectivenotion.model.page.property.TitleProperty;
import ind.venture.objectivenotion.model.page.type.Link;
import ind.venture.objectivenotion.model.page.type.Relation;
import ind.venture.objectivenotion.model.page.type.RichText;
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
        Map<String, PageProperty> properties = new HashMap<>();
        properties.put("목표", createTitleProperty(null, title, null));
        properties.put("상위 항목", new RelationProperty(null, List.of(new Relation(parentPageId)), null));

        return new CreatePageRequest(new CreatePageRequest.Parent(databaseId), properties);
    }

    public static PageProperty createTitleProperty(String id, String text, String href) {
        return new TitleProperty(id, List.of(createRichText(text, href)));
    }

    public static RichText createRichText(String text, String href) {
        return new RichText(
                createText(text, href),
                text,
                href
        );
    }

    public static Text createText(String content, String href) {
        return new Text(content, href != null ? new Link(href) : null);
    }
}
