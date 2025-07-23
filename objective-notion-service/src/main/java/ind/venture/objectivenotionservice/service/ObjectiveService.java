package ind.venture.objectivenotionservice.service;

import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import ind.venture.objectivenotionservice.support.NotionObjectiveManager;
import ind.venture.objectivenotionservice.support.SubObjectiveGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ObjectiveService {
    private final NotionObjectiveManager notionManager;
    private final SubObjectiveGenerator subObjectiveGenerator;

    public Mono<Void> createSubObjective(NotionWebhookEvent event) {
        return notionManager.getCreatableSubObjectivePage(event)
                .flatMap(page -> {
                    String mainObjectiveTitle = notionManager.extractMainObjectiveTitle(page);
                    String databaseId = page.getParent().getDatabase_id();
                    String mainPageId = page.getId();

                    return prepareSubObjectivesAndDelete(page, mainObjectiveTitle)
                            .flatMap(subObjectives ->
                                    createAllSubPages(databaseId, mainPageId, subObjectives)
                            )
                            .doOnSuccess(v -> log.info("하위 목표 페이지 생성 완료!"));
                });
    }

    private Mono<List<String>> prepareSubObjectivesAndDelete(Page page, String mainObjectiveTitle) {
        Mono<Void> deleteMono = notionManager.deleteAllSubObjectives(page);
        Mono<List<String>> generateMono = subObjectiveGenerator.generateSubObjectives(mainObjectiveTitle);

        return Mono.when(generateMono, deleteMono)
                .then(generateMono);
    }

    private Mono<Void> createAllSubPages(String databaseId, String mainPageId, List<String> subObjectives) {
        return Flux.fromIterable(subObjectives)
                .flatMap(subObjective -> notionManager.createSubPage(databaseId, mainPageId, subObjective))
                .then();
    }
}
