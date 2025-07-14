package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.support.NotionObjectiveManager;
import ind.venture.objectivenotionservice.support.SubObjectiveGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class ObjectiveService {
    private final NotionObjectiveManager notionManager;
    private final SubObjectiveGenerator subObjectiveGenerator;

    public Mono<Void> createSubObjective(String apiKey, NotionWebhookEvent event) {

        return notionManager.getCreatableSubObjectivePage(apiKey, event)
                .flatMap(page -> notionManager.deleteAllSubObjectives(apiKey, page));
    }
}
