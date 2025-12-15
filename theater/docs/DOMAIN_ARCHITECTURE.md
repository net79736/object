# 도메인 아키텍처 정리

## 도메인 개념 설명

### 1. Money (금액)
- 금액을 나타내는 값 객체(Value Object)입니다.
- 불변 객체로 설계되어 부작용을 방지합니다.
- 금액 계산(더하기, 빼기, 곱하기) 및 비교 기능을 제공합니다.
- `BigDecimal`을 사용하여 정확한 금액 계산을 보장합니다.

### 2. Movie (영화)
- 영화 정보를 나타내는 도메인 모델입니다.
- 영화 제목, 상영 시간, 기본 요금, 할인 정책을 포함합니다.
- 할인 정책을 통해 할인된 요금을 계산합니다.
- **다형성**: `DiscountPolicy` 추상 클래스를 통해 다양한 할인 정책을 받아들입니다.

### 3. Screening (상영)
- 특정 영화의 상영 정보를 나타냅니다.
- 영화, 상영 순번, 상영 시작 시간을 포함합니다.
- 고객과 인원수를 받아 예매를 처리합니다.
- 예매 시 할인 정책을 적용하여 최종 요금을 계산합니다.

### 4. Reservation (예매)
- 영화 예매 정보를 나타냅니다.
- 고객, 상영 정보, 요금, 인원 수를 포함합니다.
- 예매 생성 시 모든 정보를 한 번에 저장합니다.

### 5. Customer (고객)
- 고객을 나타내는 도메인 모델입니다.
- 현재는 기본 구조만 포함되어 있습니다.

### 6. DiscountPolicy (할인 정책)
- 할인 정책을 나타내는 추상 클래스입니다.
- **템플릿 메서드 패턴**: `calculateDiscountAmount` 메서드에서 할인 조건을 확인하고, 실제 할인 금액 계산은 하위 클래스에 위임합니다.
- 여러 할인 조건을 가질 수 있습니다.
- 할인 조건을 만족하면 할인 금액을 계산하고, 만족하지 않으면 0원을 반환합니다.

#### 6.1. AmountDiscountPolicy (고정 금액 할인 정책)
- 고정된 금액을 할인하는 정책입니다.
- 예: 800원 할인

#### 6.2. PercentDiscountPolicy (비율 할인 정책)
- 영화 요금의 일정 비율을 할인하는 정책입니다.
- 예: 10% 할인

### 7. DiscountCondition (할인 조건)
- 할인 조건을 나타내는 인터페이스입니다.
- **전략 패턴**: 다양한 할인 조건을 동적으로 적용할 수 있습니다.
- 상영 정보를 받아 할인 조건을 만족하는지 확인합니다.

#### 7.1. PeriodCondition (기간 조건)
- 특정 요일과 시간 범위에 따른 할인 조건입니다.
- 예: 화요일 14:00 ~ 16:59

#### 7.2. SequenceCondition (순번 조건)
- 특정 상영 순번에 따른 할인 조건입니다.
- 예: 1회차, 10회차

---

## 도메인 관계 흐름

```
Customer (고객)
  └──> Reservation (예매)
        └──> Screening (상영)
              └──> Movie (영화)
                    ├──> DiscountPolicy (할인 정책)
                    │     ├──> AmountDiscountPolicy (고정 금액 할인)
                    │     └──> PercentDiscountPolicy (비율 할인)
                    │           └──> DiscountCondition[] (할인 조건들)
                    │                 ├──> PeriodCondition (기간 조건)
                    │                 └──> SequenceCondition (순번 조건)
                    └──> Money (기본 요금)
```

---

## 설계 원칙 적용

### 1. 단일 책임 원칙 (SRP)
- 각 클래스는 하나의 책임만 가집니다.
  - `Movie`: 영화 정보 관리 및 요금 계산
  - `Screening`: 상영 정보 관리 및 예매 처리
  - `DiscountPolicy`: 할인 금액 계산
  - `DiscountCondition`: 할인 조건 확인

### 2. 개방-폐쇄 원칙 (OCP)
- 새로운 할인 정책이나 할인 조건을 추가할 때 기존 코드를 수정하지 않고 확장할 수 있습니다.
- `DiscountPolicy`를 상속하여 새로운 할인 정책 추가 가능
- `DiscountCondition`을 구현하여 새로운 할인 조건 추가 가능

### 3. 리스코프 치환 원칙 (LSP)
- `DiscountPolicy`의 하위 클래스(`AmountDiscountPolicy`, `PercentDiscountPolicy`)는 언제든지 상위 클래스로 치환 가능합니다.
- `DiscountCondition`의 구현체(`PeriodCondition`, `SequenceCondition`)는 언제든지 인터페이스로 치환 가능합니다.

### 4. 인터페이스 분리 원칙 (ISP)
- `DiscountCondition` 인터페이스는 단일 메서드만 포함하여 간결합니다.

### 5. 의존성 역전 원칙 (DIP)
- `Movie`는 `DiscountPolicy` 추상 클래스에 의존합니다 (구체 클래스가 아닌 추상에 의존).
- `DiscountPolicy`는 `DiscountCondition` 인터페이스에 의존합니다 (구체 클래스가 아닌 인터페이스에 의존).

---

## 패턴 적용

### 1. 값 객체 (Value Object) 패턴
- `Money` 클래스는 값 객체로 설계되어 불변성을 보장합니다.

### 2. 전략 패턴 (Strategy Pattern)
- `DiscountPolicy`와 `DiscountCondition`을 통해 다양한 할인 전략을 동적으로 적용할 수 있습니다.

### 3. 템플릿 메서드 패턴 (Template Method Pattern)
- `DiscountPolicy.calculateDiscountAmount()`는 템플릿 메서드로, 할인 조건 확인 로직은 공통으로 처리하고 실제 할인 금액 계산은 하위 클래스에 위임합니다.

