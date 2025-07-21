package ind.venture.objectivenotionservice.client;

import reactor.core.publisher.Mono;

public interface OpenAiObjectivePromptClient {
    Mono<String> getObjectivesFromPromptId(String goal);
}
