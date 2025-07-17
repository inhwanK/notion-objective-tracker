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
                .flatMap(page -> {

                    String mainObjectiveTitle = notionManager.extractMainObjectiveTitle(page);
                    return Mono.zip(
                            notionManager.deleteAllSubObjectives(page),
                            subObjectiveGenerator.generateSubObjectives(mainObjectiveTitle)
                    ).flatMap(tuple -> {
                        List<String> subObjectives = tuple.getT2();
                        return Mono.just(List.of("성공 "));
//                        notionManager.createSubObjectives(page, subObjectives);
                    });
                });

    }
}
