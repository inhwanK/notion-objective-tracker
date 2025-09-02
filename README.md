## 🧠 Objective Notion Tracker
Notion DB 기반 목표를 자동 세분화하고 하위 목표를 생성하는 자동화 서비스입니다.

![시연 영상.gif](%EC%8B%9C%EC%97%B0%20%EC%98%81%EC%83%81.gif)
### 📦 기술 스택
- Java 17
- Spring Boot 3.x (WebFlux)
- Notion API
- OpenAI API 
- AWS

### 🗂️ 모듈 구성
- objective-notion-model  
  → Notion 페이지, 속성 등을 모델링한 순수 객체 모듈  

- objective-notion-service  
  → WebClient, 비즈니스 로직, API 서버 구현 포함

<br>

### ⚙️ 실행 전 준비 사항  
1. **노션 DB 템플릿 복제**
   - 본 프로젝트는 아래 Notion DB 템플릿 구조에 맞게 동작합니다. 필수 속성은 '목표', '하위 목표 생성', '하위 목표 생성 시간' 입니다.
   - [🔗 Notion 템플릿 링크](https://hollow-truffle-4cf.notion.site/notion-objective-tracker-19211e3ff10e8011b5ecc0a1c1d39e0f?source=copy_link)를 클릭해 자신의 워크스페이스로 템플릿을 복제하세요.
2. **Notion Integration 생성 및 권한 연결**
    - [Notion Integration 생성하기](https://www.notion.com/my-integrations)
    - Integration을 생성할 때, **[사용 권한] 탭에서 복제한 DB(페이지)를 검색해 선택**하세요.
    - 이후 발급받은 Internal Integration Token(API 키)을 `application.yml`에 등록하면 됩니다.

3. **OpenAI API 키 발급**
   - [OpenAI API 키 발급](https://platform.openai.com/api-keys)
4. **application.yml 설정**  
   - objective-notion-service/src/main/resources/application.yml 파일을 아래와 같이 작성하세요:

```yaml
spring:
  application:
    name: objective-notion-service
  ai:
    openai:
      api-key: sk-xxxxxx...

notion:
  api:
    key: secret_xxxxx...
```
🔐 민감한 키는 절대 커밋하지 말고, .gitignore로 예외 처리하세요.  
예시 파일: application-example.yml

<br>

### 🏗️ 빌드 및 실행
```bash
./gradlew clean build
cd objective-notion-service
java -jar build/libs/objective-notion-service-0.0.1-SNAPSHOT.jar
```

<br>

### 🌐 웹훅 등록 및 HTTPS 배포 (노션 API)
노션 웹훅을 등록하기 위해선 공개 HTTPS 주소가 필요합니다.
로컬 테스트가 불가하므로 AWS EC2 + 인증서 (예: certbot) 등을 활용해 서버를 띄우고 아래 절차를 따르세요.

Notion 웹훅 등록 시, 아래 엔드포인트를 입력하세요. (외부에서 접근 가능한 HTTPS 서버에 배포되어 있어야 합니다.)
  
> https://your-domain-or-public-ip/api/webhook/event
- `<your-domain-or-public-ip>`는 실제 배포 서버의 도메인 또는 퍼블릭 IP로 교체하세요.
- 반드시 HTTPS 프로토콜을 사용해야 하며, HTTP로는 Notion에서 등록할 수 없습니다.


**/api/webhook/event** 엔드포인트는 컨트롤러(NotionWebhooksReceiverController)에 구현되어 있습니다.
Notion 웹훅 등록 과정에서는 아래처럼 인증용 핸들러로 잠시 변경해야 합니다. 변경 후 요청 바디로 들어오는 validation_token을 복사하여 인증하시면 됩니다.
```java
@PostMapping("/webhook/event")
public String receiveEvent(@RequestBody String validation_token) {
    log.info("Received webhook validation token: {}", validation_token);
    return validation_token;
}
```

노션 웹훅 등록이 완료된 후에는, 아래처럼 실제 이벤트 처리를 수행하는 코드로 다시 변경하고 등록시 사용한 코드를 주석 처리해 주세요.
```java
@PostMapping("/webhook/event")
public Mono<Void> receiveEvent(@RequestBody NotionWebhookEvent event) {
    log.info("Received webhook event: {}", event);
    if ("page.properties_updated".equals(event.getType())) {
        return objectiveService.createSubObjective(event);
    }
    return Mono.empty();
}
```

<br>

### ✅ 주요 기능
- Notion 목표 데이터에서 하위 목표 자동 생성 
- WebClient 기반 OpenAI Prompt 호출 
- AWS HTTPS 기반 Notion Webhook 이벤트 수신
- 날짜(일별, 주별) 기반 페이지 조회 기능(보완 예정)

### 🛠️ 보완점 및 향후 계획
- 날짜별 페이지 조회를 통한 리마인더 기능은 아직 미완성 단계이며, 추후 하위 목표 자동 알림 및 사용자 맞춤 알림 기능을 추가할 계획입니다.
