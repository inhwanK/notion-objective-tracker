package ind.venture.objectivenotionservice.util;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class LogUtils {
    public static <T> Mono<T> logWithContext(String context, Mono<T> mono) {
        return mono
                .doOnSubscribe(s -> log.info("[{}] 요청 시작", context))
                .doOnNext(res -> log.info("[{}] 응답: {}", context, res))
                .doOnError(err -> log.error("[{}] 에러: {}", context, err.getMessage()));
    }
}
