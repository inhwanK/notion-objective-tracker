package ind.venture.objectivenotionservice.util;


import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.page.property.*;
import ind.venture.objectivenotion.model.page.type.*;
import ind.venture.objectivenotion.request.CreatePageRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageFactory {
    public static Page createBasePage(String pageId, String titleText, boolean archived, String requestId) {
        String safeTitle = titleText != null ? titleText : "";

        Map<String, PageProperty> props = new HashMap<>();
        props.put("Title", createTitleProperty("prop-title-" + pageId, safeTitle, null));

        String now = Instant.now().toString();

        return new Page(
                pageId,
                now,
                now,
                archived,
                "https://notion.so/" + pageId,
                null,
                props,
                requestId
        );
    }

    public static PageProperty createTitleProperty(String id, String text, String href) {
        return new TitleProperty(id, List.of(createRichText(text, href)));
    }

    public static PageProperty createDateProperty(String id, Date date) {
        return new DateProperty(id, date);
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
