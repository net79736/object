# 프로젝트 구조

## 기술 스택

### 주요 기술
- Java
- 객체지향 프로그래밍 원칙

## 프로젝트 구조

```
theater/
├── docs/                                    # 문서
│   ├── INDEX.md                             # 문서 인덱스
│   ├── PROJECT_STRUCTURE.md                 # 프로젝트 구조
│   ├── DOMAIN_ARCHITECTURE.md               # 도메인 아키텍처
│   ├── CLASS_DIAGRAM.md                     # 클래스 다이어그램
│   └── domain-class-diagram.png             # 도메인 클래스 다이어그램 이미지
│
├── v1/                                      # 버전 1
│   ├── Audience.java
│   ├── Bag.java
│   ├── Invitation.java
│   ├── Theater.java
│   ├── Ticket.java
│   ├── TicketOffice.java
│   ├── TicketSeller.java
│   └── README.md
│
├── v2/                                      # 버전 2
│   ├── Audience.java
│   ├── Bag.java
│   ├── Invitation.java
│   ├── Theater.java
│   ├── Ticket.java
│   ├── TicketOffice.java
│   ├── TicketSeller.java
│   └── README.md
│
├── v3/                                      # 버전 3
│   ├── Audience.java
│   ├── Bag.java
│   ├── Invitation.java
│   ├── Theater.java
│   ├── Ticket.java
│   ├── TicketOffice.java
│   ├── TicketSeller.java
│   └── README.md
│
└── v4/                                      # 버전 4 (현재 버전)
    ├── condition/                           # 할인 조건 패키지
    │   ├── DiscountCondition.java           # 할인 조건 인터페이스
    │   ├── PeriodCondition.java             # 기간 조건 구현
    │   ├── SequenceCondition.java           # 순번 조건 구현
    │   └── README.md
    │
    ├── policy/                              # 할인 정책 패키지
    │   ├── DiscountPolicy.java              # 할인 정책 추상 클래스
    │   ├── AmountDiscountPolicy.java        # 고정 금액 할인 정책
    │   ├── PercentDiscountPolicy.java       # 비율 할인 정책
    │   └── README.md
    │
    ├── Customer.java                        # 고객
    ├── Money.java                           # 금액 값 객체
    ├── Movie.java                           # 영화
    ├── Reservation.java                     # 예매
    ├── Screening.java                       # 상영
    ├── Test.java                            # 테스트 클래스
    └── README.md                            # API 문서
```

## 버전별 개선 사항

### v1
- 초기 구현 버전

### v2
- 객체 간 결합도 개선

### v3
- 책임 분리 개선

### v4 (현재)
- **다형성을 활용한 할인 정책 설계**
  - `DiscountPolicy` 추상 클래스를 통한 할인 정책 추상화
  - `AmountDiscountPolicy`, `PercentDiscountPolicy` 구현
- **인터페이스를 활용한 할인 조건 설계**
  - `DiscountCondition` 인터페이스를 통한 할인 조건 추상화
  - `PeriodCondition`, `SequenceCondition` 구현
- **값 객체(Value Object) 패턴 적용**
  - `Money` 클래스를 값 객체로 설계
- **개방-폐쇄 원칙(OCP) 준수**
  - 새로운 할인 정책이나 할인 조건을 추가할 때 기존 코드 수정 없이 확장 가능

