package ind.venture.remindercore.util;

import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.domain.property.*;

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
        return new PageProperty(
                id,
                "title",
                List.of(createRichText(text, href)),
                null, null, null, null, null, null
        );
    }

    public static PageProperty createDateProperty(String id, Date date) {
        return new PageProperty(
                id,
                "date",
                null,
                date,
                null, null, null, null, null
        );
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
