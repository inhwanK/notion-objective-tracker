package ind.venture.objectivenotionclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "https://api.notion.com/v1/";
    private static final String CONTENT_TYPE = "application/json";
    private static final String NOTION_VERSION = "2022-06-28";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Content-Type", CONTENT_TYPE)
                .defaultHeader("Notion-Version", NOTION_VERSION)
                .filter(exceptionLoggingFilter())
                .build();
    }

    private ExchangeFilterFunction exceptionLoggingFilter() {
        return (request, next) ->
                next.exchange(request)
                        .flatMap(response -> {
                            if (isErrorStatus(response.statusCode())) {
                                return handleErrorResponse(request, response);
                            }
                            return Mono.just(response);
                        });
    }

    private boolean isErrorStatus(HttpStatusCode statusCode) {
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    private Mono<ClientResponse> handleErrorResponse(
            ClientRequest request,
            ClientResponse response
    ) {
        return response.bodyToMono(NotionErrorResponse.class)
                .flatMap(error -> {
//                    log.error("Notion API Error | status={} | code={} | message={} | requestId={} | uri={}",
//                            error.getStatus(),
//                            error.getCode(),
//                            error.getMessage(),
//                            error.getRequestId(),
//                            request.url());

                    return Mono.error(new RuntimeException("Notion API Error: "/* + error.getMessage()*/));
                });
    }
}
