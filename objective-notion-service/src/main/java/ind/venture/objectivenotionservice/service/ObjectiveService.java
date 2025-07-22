package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.support.NotionObjectiveManager;
import ind.venture.objectivenotionservice.support.SubObjectiveGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ObjectiveService {
    private final NotionObjectiveManager notionManager;
    private final SubObjectiveGenerator subObjectiveGenerator;

    public Mono<List<String>> createSubObjective(NotionWebhookEvent event) {
        return notionManager.getCreatableSubObjectivePage(event)
                .doOnSubscribe(s -> log.info("getCreatableSubObjectivePage 구독!"))
                .flatMap(page -> {
                    log.info("flatMap 진입! page: {}", page);
                    String mainObjectiveTitle = notionManager.extractMainObjectiveTitle(page);

                    notionManager.deleteAllSubObjectives(page)
                            .subscribe();

                    return subObjectiveGenerator.generateSubObjectives(mainObjectiveTitle)
                            .flatMap(subObjectives -> {

                                log.info("OpenAI 결과(하위 목표): {}", subObjectives);
                                return Mono.empty();
                            });
                });

    }
}
