package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionservice.client.NotionPageClient;
import ind.venture.objectivenotionservice.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class NotionPageService {
    private final NotionPageClient notionPageClient;

    public NotionPageService(NotionPageClient notionPageClient) {
        this.notionPageClient = notionPageClient;
    }


    public Mono<Page> findPageById(String pageId) {
        return LogUtils.logWithContext("페이지 조회: " + pageId, notionPageClient.fetchPage(pageId));
    }
}
