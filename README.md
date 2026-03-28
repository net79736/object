# 오브젝트 스터디

> 조영호 『오브젝트』 기반 코드 학습 저장소

## 챕터 지도

| 챕터 | 주제 | 핵심 |
|------|------|------|
| ch01 | 객체 책임 분리 | Theater가 Bag 뒤지는 거 왜 나쁜가 |
| ch02 | 할인 정책 설계 | switch 없이 새 할인 추가하기 (Strategy) |
| ch03 | 상속 vs 합성 | 통화요금으로 보는 클래스 폭발 문제 |
| ch04 | SOLID 원칙 | SRP / OCP / DIP — 실제 코드로 보기 |
| ch05 | 메시지와 인터페이스 | 디미터 법칙, 묻지말고 시켜라 |
| ch06 | 의존성 역전 | 인터페이스 소유권, Factory, ServiceLocator |
| ch07 | 디자인 패턴 | Strategy / Decorator / Chain — 합성 기반 |
| ch08 | 통합 설계 | 쇼핑 시스템으로 ch01~07 한 번에 적용 |

## 디렉터리 구조 (각 챕터)

```
ch0N_xxx/
  before/   ← 문제 있는 코드
  after/    ← 개선된 코드
  docs/     ← (선택) 다이어그램·메모
```

## 공통 코드

- `common/` — `Money`, `Audience`, `Bag`, `Invitation`, `Ticket` (전 챕터 공유)

## 리팩토링 스크립트

동일한 디렉터리 이동·패키지 갱신을 재현하려면:

`scripts/refactor_object_study_layout.py`

## IntelliJ 설정 팁

1. **File → Project Structure → Modules → Sources** 에서 저장소 루트를 **Sources** 로 지정하거나, `common/` 과 사용 중인 챕터 폴더만 소스로 표시합니다.
2. **Refactor → Move** 로 추가 이동 시 import 가 자동 정렬됩니다.
3. **Build → Rebuild Project** 로 컴파일을 확인합니다.

---

상세 가이드(STEP별 이동 목록, before/after 설명)는 스터디 노트에 맞춰 이 README 를 확장하면 됩니다.
