package ind.venture.objectivenotionservice.support;

import ind.venture.objectivenotionservice.client.OpenAiObjectivePromptClient;
import ind.venture.objectivenotionservice.client.OpenAiWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SubObjectiveGenerator {

    private final OpenAiObjectivePromptClient openAiObjectivePromptClient;

    public Mono<List<String>> generateSubObjectives(String mainObjectiveTitle) {
        log.info("Generate sub objectives for main objective title: {}", mainObjectiveTitle);

        return openAiObjectivePromptClient
                .getObjectivesFromPromptId(mainObjectiveTitle)
                .map(response -> {
                    log.info("OpenAI sub objective response: {}", response);
                    return parseSubObjectives(response);
                });
    }

    private List<String> parseSubObjectives(String response) {
        List<String> result = new ArrayList<>();
        if (response == null || response.isBlank()) {
            return result;
        }
        String[] lines = response.split("\\r?\\n");
        for (String line : lines) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                log.info("result : {}", trimmed);
                result.add(trimmed);
            }
        }
        return result;
    }
}
