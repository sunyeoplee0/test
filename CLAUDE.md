# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요

Spring Boot 4.0.5 (Java 17) 기반의 백엔드 프로젝트로, 실기 종합 평가 과제(`종합 평가.md`)를 구현하기 위한 시작 템플릿이다. 현재는 `BackendApplication` 진입점만 존재하며, 게시판 기능은 아직 구현되지 않았다.

## 구현해야 할 기능 (종합 평가.md 기준)

게시판 REST API를 구현해야 하며, 프론트엔드 프로젝트가 이 API를 호출한다.

| Method | URL | 설명 | 응답 |
| --- | --- | --- | --- |
| GET | `/board/list` | 전체 게시글 최신순 목록 | `[{idx, title, content}, ...]` (200) |
| GET | `/board/{idx}` | 특정 게시글 상세 (`idx`: Long) | `{idx, title, content}` (200) |
| POST | `/board/reg` | 게시글 등록, body: `{title, content}` | `{status, message, idx}` (201) |

추가로 과제 04~07번에서 K8S 매니페스트 및 Jenkins CI/CD 파이프라인 구성이 필요하다 (코드 변경 → push → 배포 서버 반영까지).

## 빌드 / 실행 / 테스트

Windows 환경이지만 셸은 bash이므로 `./gradlew` 사용.

```bash
./gradlew build           # 빌드
./gradlew bootRun         # 로컬 실행
./gradlew test            # 전체 테스트
./gradlew test --tests com.example.backend.BackendApplicationTests.contextLoads   # 단일 테스트
```

## 구조 메모

- 백엔드 패키지 루트: `com.example.backend`
- 설정 파일: `src/main/resources/application.yml` (MariaDB datasource + `jpa.hibernate.ddl-auto: update`)
- 백엔드 의존성: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `mariadb-java-client`, `lombok`
- 프론트엔드: `vue-project/` (Vue 3 + vue-router 4 + Vite, 순수 CSS, axios)
  - API 호출은 `src/api/*.js`에 axios 인스턴스(`baseURL: '/api'`)로 집중한다. 컴포넌트에서 직접 `axios`를 import 하지 않는다.
  - dev: Vite proxy (`/api` → `http://localhost:8080`, `vite.config.js`의 `server.proxy`). 운영: K8S ingress가 동일 규칙으로 백엔드에 리버스 프록시하므로 CORS 설정 불필요.
  - 라우트: `/board/list`, `/board/reg`, `/board/:idx` (reg를 :idx보다 먼저 선언해야 함)
  - 실행: `cd vue-project && npm run dev` (5173)

## 코딩컨벤션
- dto는 dto클래스파일 하나만 생성하고 안에 record들로 구성
- 아키텍처는 mvc 레이어드 아키텍처 패턴으로 작성할것 (jpa사용)
- 주석 및 swagger같은 잡다한건 필요없고 오로지 종합평가에 해당하는 기능만 작성할것
- 엔티티 ↔ DTO 변환은 Dto 클래스 내부에서 수행 (응답 record는 static `from(Entity)`, 요청 record는 인스턴스 `toEntity()` 메서드). 서비스 계층에서는 직접 필드를 꺼내 새 DTO/엔티티를 만들지 않는다.