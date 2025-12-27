# 배송비 계산 시스템 - 합성 기반 설계

## 📋 목차
1. [현재 설계의 문제점](#현재-설계의-문제점)
2. [합성 기반 설계 개요](#합성-기반-설계-개요)
3. [클래스 구조](#클래스-구조)
4. [사용 예시](#사용-예시)
5. [설계 원칙](#설계-원칙)

---

## 현재 설계의 문제점

### 문제점 1: 상속 계층이 깊어져 복잡도 증가
```java
// 현재 상속 기반 설계
RainyDayDelivery extends WeekendDelivery extends ExpressDelivery extends DeliveryFee
```
- 새로운 정책을 추가하려면 상속 계층을 따라가야 함
- `RainyDayDelivery`를 사용하려면 `WeekendDelivery`와 `ExpressDelivery`도 필요
- 정책이 독립적이지 않아 재사용이 어려움

### 문제점 2: 정책 조합이 불가능
- 특급 + 우천만 필요한 경우에도 `WeekendDelivery`를 상속받아야 함
- 특급 + 심야 조합을 만들려면 새로운 클래스 생성 필요
- N개의 정책이 있으면 2^N개의 클래스가 필요 (조합 폭발)

### 문제점 3: 정책 적용 순서가 고정되어 변경 불가능
- 상속 구조에서 정책 적용 순서는 상속 계층에 의해 결정됨
- 할인을 먼저 적용할지, 할증을 먼저 적용할지 선택 불가능
- 비즈니스 요구사항 변경 시 코드 수정 필요

---

## 합성 기반 설계 개요

### 핵심 아이디어
- 각 정책을 **독립적인 클래스**로 구현
- 정책들을 **리스트로 관리**하여 런타임에 조합
- **Chain of Responsibility 패턴**으로 순차 적용

### 장점
1. ✅ 정책을 독립적으로 구현하여 재사용성 향상
2. ✅ 런타임에 정책을 자유롭게 조합 가능
3. ✅ 정책 적용 순서를 동적으로 변경 가능
4. ✅ 새로운 정책 추가 시 기존 코드 수정 불필요

---

## 클래스 구조

```
delivery/
├── DeliveryFeeCalculator.java          # 배송비 계산기 (서비스)
├── common/
│   └── DeliveryFeePolicyChain.java    # 정책 체인 관리
├── policy/
│   ├── intf/
│   │   └── DeliveryFeePolicy.java     # 정책 인터페이스
│   ├── BaseDeliveryFeePolicy.java     # 기본 배송비 정책
│   ├── ExpressDeliveryPolicy.java     # 특급 배송 정책
│   ├── WeekendDeliveryPolicy.java    # 주말 배송 정책
│   ├── RainyDayDeliveryPolicy.java   # 우천 배송 정책
│   └── NightTimeDeliveryPolicy.java   # 심야 배송 정책
└── docs/
    └── README.md
```

### 클래스 설명

#### 1. DeliveryFeePolicy (인터페이스)
- 모든 배송비 정책이 구현해야 하는 인터페이스
- `calculateFee(int baseFee)` 메서드로 배송비 계산

#### 2. BaseDeliveryFeePolicy
- 거리를 기반으로 기본 배송비 계산 (거리 * 1,000원)
- 다른 정책들의 기반이 되는 기본 정책

#### 3. ExpressDeliveryPolicy
- 특급 배송비 5,000원 추가

#### 4. WeekendDeliveryPolicy
- 주말 할증 50% 적용

#### 5. RainyDayDeliveryPolicy
- 우천 추가 요금 3,000원 추가

#### 6. NightTimeDeliveryPolicy
- 심야 배송비 7,000원 추가 (22시~6시)

#### 7. DeliveryFeePolicyChain
- 정책들을 체인으로 관리
- 순차적으로 정책을 적용

#### 8. DeliveryFeeCalculator
- 배송비 계산을 담당하는 서비스 클래스
- 기본 정책과 추가 정책들을 조합하여 최종 배송비 계산

---

## 사용 예시

### 예시 1: 기본 배송비 계산
```java
DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
int fee = calculator.calculate(5); // 5km
// 결과: 5,000원
```

### 예시 2: 특급 배송비 계산
```java
DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
int fee = calculator.calculate(5, new ExpressDeliveryPolicy());
// 결과: 10,000원 (5,000원 + 5,000원)
```

### 예시 3: 특급 + 우천 + 심야 배송비 계산 (요구사항)
```java
DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();

// 방법 1: 가변 인자 사용
int fee = calculator.calculate(
    5,
    new ExpressDeliveryPolicy(),
    new RainyDayDeliveryPolicy(),
    new NightTimeDeliveryPolicy()
);
// 결과: 20,000원
// 계산 과정:
//   기본: 5,000원
//   + 특급: 5,000원 → 10,000원
//   + 우천: 3,000원 → 13,000원
//   + 심야: 7,000원 → 20,000원

// 방법 2: 체인 사용 (더 명시적)
DeliveryFeePolicyChain chain = new DeliveryFeePolicyChain()
    .addPolicy(new ExpressDeliveryPolicy())
    .addPolicy(new RainyDayDeliveryPolicy())
    .addPolicy(new NightTimeDeliveryPolicy());

int fee = calculator.calculate(5, chain);
// 결과: 20,000원
```

### 예시 4: 정책 순서 변경
```java
DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();

// 순서 1: 특급 → 주말 할증
int fee1 = calculator.calculate(
    5,
    new ExpressDeliveryPolicy(),
    new WeekendDeliveryPolicy()
);
// 결과: 15,000원
// 계산: (5,000 + 5,000) * 1.5 = 15,000원

// 순서 2: 주말 할증 → 특급
int fee2 = calculator.calculate(
    5,
    new WeekendDeliveryPolicy(),
    new ExpressDeliveryPolicy()
);
// 결과: 12,500원
// 계산: (5,000 * 1.5) + 5,000 = 12,500원
```

### 예시 5: 다양한 정책 조합
```java
DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();

// 특급 + 심야
int fee1 = calculator.calculate(
    3,
    new ExpressDeliveryPolicy(),
    new NightTimeDeliveryPolicy()
);

// 우천 + 심야
int fee2 = calculator.calculate(
    3,
    new RainyDayDeliveryPolicy(),
    new NightTimeDeliveryPolicy()
);

// 모든 정책 적용
int fee3 = calculator.calculate(
    3,
    new ExpressDeliveryPolicy(),
    new WeekendDeliveryPolicy(),
    new RainyDayDeliveryPolicy(),
    new NightTimeDeliveryPolicy()
);
```

---

## 설계 원칙

### 1. 단일 책임 원칙 (SRP)
- 각 정책 클래스는 하나의 책임만 가짐
- `ExpressDeliveryPolicy`는 특급 배송비만 담당
- `WeekendDeliveryPolicy`는 주말 할증만 담당

### 2. 개방-폐쇄 원칙 (OCP)
- 새로운 정책 추가 시 기존 코드 수정 불필요
- `DeliveryFeePolicy` 인터페이스를 구현하는 새 클래스만 추가

### 3. 의존성 역전 원칙 (DIP)
- `DeliveryFeeCalculator`는 구체 클래스가 아닌 `DeliveryFeePolicy` 인터페이스에 의존
- `DeliveryFeePolicyChain`도 인터페이스에 의존

### 4. 전략 패턴 (Strategy Pattern)
- 각 정책을 독립적인 전략으로 구현
- 런타임에 전략을 선택하여 적용

### 5. 체인 오브 책임 패턴 (Chain of Responsibility)
- `DeliveryFeePolicyChain`으로 정책들을 체인으로 관리
- 순차적으로 정책을 적용

---

## 테스트 실행

```bash
cd theater/delivery
javac -d . **/*.java
java delivery.DeliveryFeeTest
```

---

## 확장 가능성

### 새로운 정책 추가 예시
```java
// 새 정책: 공휴일 배송 정책
public class HolidayDeliveryPolicy implements DeliveryFeePolicy {
    private static final int HOLIDAY_FEE = 2000;
    
    @Override
    public int calculateFee(int baseFee) {
        return baseFee + HOLIDAY_FEE;
    }
}

// 사용
int fee = calculator.calculate(
    5,
    new ExpressDeliveryPolicy(),
    new HolidayDeliveryPolicy()  // 새 정책 추가
);
```

기존 코드 수정 없이 새로운 정책을 추가할 수 있습니다!

