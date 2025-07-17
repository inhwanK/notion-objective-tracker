package ind.venture.objectivenotionservice.support;

import ind.venture.objectivenotionservice.client.OpenAiObjectivePromptClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SubObjectiveGenerator {

    private final OpenAiObjectivePromptClient openAiObjectivePromptClient;

    public Mono<List<String>> generateSubObjectives(String mainObjectiveTitle) {
        return openAiObjectivePromptClient
                .getObjectivesFromPromptId(mainObjectiveTitle)
                .map(this::parseSubObjectives);
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
                result.add(trimmed);
            }
        }
        return result;
    }

}
