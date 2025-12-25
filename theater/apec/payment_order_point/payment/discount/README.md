# 결제 수단별 할인 정책 개선안

## 개선 배경

기존 구조에서는 `PaymentHandler`가 결제 처리와 할인 정책 두 가지 책임을 가지고 있었습니다.
또한 할인 정책이 `PaymentType` enum에 하드코딩되어 있어, 이벤트별로 다른 할인 정책을 적용하기 어려웠습니다.

## 개선 사항

### 1. 단일 책임 원칙 (SRP) 준수
- `PaymentHandler`: 결제 처리만 담당
- `PaymentDiscountPolicy`: 할인 정책만 담당

### 2. Strategy 패턴 적용
- 할인율 기반: `RatePaymentDiscountPolicy`
- 고정 금액 기반: `FixedAmountPaymentDiscountPolicy`
- 할인 없음: `NonePaymentDiscountPolicy`

### 3. 이벤트별 동적 할인 정책 적용
- `PaymentDiscountPolicyRepository`를 통해 할인 정책 관리
- 이벤트별로 다른 할인 정책을 동적으로 적용 가능

## 사용 예제

### 기본 사용법

```java
// 1. 할인 정책 저장소 생성
PaymentDiscountPolicyRepository repository = new PaymentDiscountPolicyRepository();

// 2. PaymentService 생성 (할인 정책 저장소 주입)
PaymentService paymentService = new PaymentService(paymentHandlers, repository);

// 3. 할인 적용 금액 계산
int originalAmount = 10000;
int finalAmount = paymentService.calculateFinalAmount(originalAmount, PaymentType.CREDIT_CARD);
// 신용카드 10% 할인 적용: 9000원
```

### 이벤트별 할인 정책 변경

```java
// 이벤트 1: 신용카드 20% 할인 이벤트
repository.register(new RatePaymentDiscountPolicy(PaymentType.CREDIT_CARD, 20));

// 이벤트 2: 계좌이체 1,000원 고정 할인 이벤트
repository.register(new FixedAmountPaymentDiscountPolicy(PaymentType.ACCOUNT_TRANSFER, 1000));

// 이벤트 3: 모든 할인 정책 교체
Map<PaymentType, PaymentDiscountPolicy> eventPolicies = new HashMap<>();
eventPolicies.put(PaymentType.CREDIT_CARD, new RatePaymentDiscountPolicy(PaymentType.CREDIT_CARD, 15));
eventPolicies.put(PaymentType.ACCOUNT_TRANSFER, new FixedAmountPaymentDiscountPolicy(PaymentType.ACCOUNT_TRANSFER, 2000));
eventPolicies.put(PaymentType.POINT, new NonePaymentDiscountPolicy(PaymentType.POINT));
repository.replaceAll(eventPolicies);
```

### 커스텀 할인 정책 구현

```java
// 복합 할인 정책 (할인율 + 고정 금액)
public class CompositePaymentDiscountPolicy implements PaymentDiscountPolicy {
    private final PaymentType paymentType;
    private final int discountRate;
    private final int fixedDiscount;
    
    @Override
    public int applyDiscount(int amount) {
        // 먼저 할인율 적용
        int afterRateDiscount = amount - (amount * discountRate / 100);
        // 그 다음 고정 금액 할인
        return Math.max(0, afterRateDiscount - fixedDiscount);
    }
}
```

## 장점

1. **확장성**: 새로운 할인 정책을 쉽게 추가할 수 있습니다.
2. **유연성**: 이벤트별로 다른 할인 정책을 동적으로 적용할 수 있습니다.
3. **테스트 용이성**: 할인 정책과 결제 처리를 독립적으로 테스트할 수 있습니다.
4. **유지보수성**: 할인 정책 변경 시 결제 처리 로직에 영향을 주지 않습니다.

## 구조 비교

### 기존 구조
```
PaymentHandler
  ├── processPayment()  (결제 처리)
  ├── getDiscountRate()  (할인 정책)
  └── getDiscountAmount() (할인 정책)
```

### 개선된 구조
```
PaymentHandler
  └── processPayment()  (결제 처리만)

PaymentDiscountPolicy
  ├── RatePaymentDiscountPolicy
  ├── FixedAmountPaymentDiscountPolicy
  └── NonePaymentDiscountPolicy

PaymentDiscountPolicyRepository
  └── 할인 정책 관리 및 조회
```

