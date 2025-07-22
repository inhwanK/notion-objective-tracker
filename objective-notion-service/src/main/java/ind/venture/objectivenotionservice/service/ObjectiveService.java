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
                    return Mono.zip(
                                    notionManager.deleteAllSubObjectives(page)
                                            .doOnSubscribe(s -> log.info("deleteAllSubObjectives 구독!")),
                                    subObjectiveGenerator.generateSubObjectives(mainObjectiveTitle)
                                            .doOnSubscribe(s -> log.info("generateSubObjectives 구독!"))
                            ).doOnSubscribe(s -> log.info("zip 구독!"))
                            .flatMap(tuple -> {
                                log.info("zip flatMap 진입!");
                                List<String> subObjectives = tuple.getT2();
                                for(String subObjective : subObjectives) {
                                    log.info("Sub objective created: {}", subObjective);
                                }
                                return Mono.just(List.of("성공 "));
                            });
                });

    }
}
