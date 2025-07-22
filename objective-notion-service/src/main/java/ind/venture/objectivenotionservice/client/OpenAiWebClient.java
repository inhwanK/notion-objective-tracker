package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotionservice.dto.OpenAIPromptRequest;
import ind.venture.objectivenotionservice.dto.OpenAIPromptResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OpenAiWebClient implements OpenAiObjectivePromptClient {

    private final WebClient openAiClient;
    private static final String SYSTEM_MESSAGE = """
            주어진 상위 목표가 이미 구체적으로 보일지라도, 반드시 그 실제 의도와 범위를 충분히 이해한 뒤, 가능한 세부 하위 목표나 더 작고 명확한 실행 단위로 분해하세요. 각 하위 목표는 하나의 명확하고 실행 가능한 행동이어야 하며, 수치화가 가능하다면 반드시 수치를 포함해야 합니다. 하위 목표는 줄바꿈(Enter)만으로 구분되며, 어떠한 불릿, 번호, 제목, 설명, 도입문, 결론문구 등은 절대 포함하지 않습니다. 오직 각 하위 목표 한 문장씩만 출력하세요.
            
            상위 목표, 안내문, 부가 설명, 결론(마무리) 구문, 불릿, 번호, 계층적 항목 또는 어떠한 추가적 설명도 절대 포함하지 않습니다.
            
            모든 하위 목표는 "~하기" 형태의 한 문장으로, 간단하고 일상 용어를 사용해 독립적으로 이해 가능하도록 작성합니다. 논리적이고 자연스러운 순서로 나열하며, 목적이 구체적으로 보여도 반드시 더 작은 실행 단위로 분해해야 합니다. 친숙한 단어를 사용하며, 중복 표현 또는 계층 구조는 절대 사용하지 않습니다.
            
            # 단계
            
            1. 상위 목표의 실제 의미, 맥락, 범위를 정확히 이해합니다. (상위 목표가 이미 구체적으로 보일 경우, 여전히 그 안에 포함된 세부 실행 항목이나 추가 분해가 필요한지 반드시 재검토합니다.)
            2. 해당 목표 달성을 위해 필수적으로 수행해야 하는 하위 목표(구체적 작업 단위)들을 도출합니다.
            3. 각 하위 목표가 실행 가능하고 구체적이며, 필요하다면 수치가 포함되는지 확인합니다.
            4. 각 하위 목표를 "~하기" 형태로, 한 줄(문장)씩 작성합니다.
            5. 모든 하위 목표는 줄바꿈(엔터)만으로 구분하고, 불릿, 번호, 설명문, 제목, 도입/결론문구 등은 절대 사용하지 않습니다.
            
            # 출력 형식
            
            - 결과는 오직 하위 목표 한 문장씩만, 줄바꿈으로 나열합니다.
            - 불릿(•, -, *, 숫자 등), 제목, 설명, 도입 및 결론, 안내문 등은 반드시 출력에 포함할 수 없습니다.
            - 상위 목표는 결과에서 절대 제외합니다.
            - 예외, 부가 안내문, 추가 구문이 포함되지 않도록 유의하세요.
            
            # 예시
            
            예시 1 \s
            - [X] (불릿이나 번호 사용 예시 - "X"가 붙은 것들은 잘못된 예시임. 아래가 정답)
            신제품 출시 일정 수립하기 \s
            신제품 생산 일정표 작성하기 \s
            출시 홍보 자료 준비하기 \s
            담당자별 역할 분배하기 \s
            
            예시 2 \s
            타겟 캠페인 실행하기 \s
            경쟁 우위 마케팅 전략 수립하기 \s
            유통 채널 확장 파트너십 구축하기 \s
            초기 소비자 피드백으로 제품 개선하기 \s
            
            (실제 예시에서는 각 문장이 반드시 줄바꿈으로만 나열되어야 하며, 어떠한 기호, 불릿, 번호, 설명, 제목 등도 포맷에 포함되면 안 됩니다)
            
            (※ 상위 목표가 '신제품 출시 계획 세우기'처럼 이미 구체적으로 보일 때에도, 실행 항목/작업 단위별로 세분화해서 나열해야 함을 반드시 지킵니다.)
            
            # 노트
            
            - 반드시 줄바꿈 이외 모든 구분자(불릿, 번호, 기호, 설명, 제목, 도입문, 결론문구 등) 사용 금지
            - 상위 목표, 안내문, 설명, 결론 등 어떤 형태의 추가 구문도 허용하지 않음
            - 각 하위 목표는 "~하기" 한 줄 문장으로, 줄바꿈만으로 세로 나열
            - 상위 목표가 구체적으로 주어져도 반드시 더 작은 실행 단위로 분해하는 단계 포함
            - 사용자는 오로지 하위 목표 나열만 얻을 수 있도록, 출력 형식을 절대 엄수
            
            # Output Format
            
            출력은 오직 여러 개의 하위 목표 문장만 줄바꿈(엔터)으로 연속 나열됩니다. 불릿, 번호, 설명, 상위 목표명, 어떤 종류의 안내문이나 부가 구문도 출력을 엄금합니다.
            
            (※ 주의: 반드시 오직 하위 목표 한 줄씩, 줄바꿈만으로 구분. 어떤 추가 구문도 허용하지 않음.)
            """;

    public OpenAiWebClient(WebClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    @Override
    public Mono<String> getObjectivesFromPromptId(String goal) {
        OpenAIPromptRequest request = OpenAIPromptRequest.of("gpt-4.1-nano", SYSTEM_MESSAGE, goal);
        return openAiClient
                .post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAIPromptResponse.class)
                .doOnNext(response -> log.info(response.toString()))
                .map(this::extractContentFromResponse);
    }

    private String extractContentFromResponse(OpenAIPromptResponse response) {
        log.info("response: {}", response);
        if (response == null || response.getOutput() == null || response.getOutput().isEmpty())
            return "";
        OpenAIPromptResponse.Output output = response.getOutput().get(0);
        if (output.getContent() == null || output.getContent().isEmpty())
            return "";
        log.info("text: {}", output.getContent().get(0).getText());
        return output.getContent().get(0).getText();
    }
}
