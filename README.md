## Notion Reminder Automation

📅 Notion 데이터베이스에 설정된 날짜(Date 속성)를 기반으로 사용자에게 리마인더 알림을 자동으로 전송해주는 백엔드 시스템입니다.

  

### 🛠️ 개발 목적

Notion의 기본 리마인더 기능은 반복성과 외부 연동에 다소 어려움이 있습니다. 이 문제를 해결하고자, Notion API + 외부 메신저(Slack/KakaoTalk 등)를 연동하는 **리마인더 자동화 시스템**을 개발하고 있습니다.

### ⚙️ 주요 기능 (진행 중)

- [x] Notion Database에서 날짜 속성 필터링
- [x] 날짜 기준 (당일, 일주일 등) 리마인더 조건 생성
- [x] Notion API 구조에 맞는 객체 매핑
- [ ] 외부 메신저 연동 (KakaoTalk, Slack 등)
- [ ] 특정 시간 주기의 알림 스케줄링
- [ ] 사용자 맞춤 리마인더 설정 기능


### 🧱 기술 스택

- Java 17, Spring Boot, Spring WebFlux, WebClient, Reactor, Gradle, Notion API
