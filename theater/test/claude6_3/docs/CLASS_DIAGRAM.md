# claude6_3 프로젝트 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 할인 정책을 적용하는 쇼핑카트 시스템을 보여줍니다. 할인 정책은 체인 패턴을 사용하여 순차적으로 적용됩니다.

```mermaid
classDiagram
    class ShoppingCart {
        - items: List~Item~
        - user: User
        + calculateTotal(): int
    }

    class User {
        - membershipLevel: MembershipLevel
        - isFirstPurchase: boolean
        - coupon: Coupon
        + calculateFinalTotal(total: int): int
    }

    ShoppingCart --> User
    User --> DiscountPolicyChain
```

## 도메인 클래스

```mermaid
classDiagram
    class User {
        - membershipLevel: MembershipLevel
        - isFirstPurchase: boolean
        - coupon: Coupon
        + calculateFinalTotal(total: int): int
        + hasCoupon(): boolean
    }

    class MembershipLevel {
        <<enumeration>>
        GOLD
        SILVER
        BRONZE
        + getDiscountRate(): int
    }

    class ShoppingCart {
        - items: List~Item~
        - user: User
        + calculateTotal(): int
    }

    class Item {
        - price: int
        - quantity: int
        + getPrice(): int
        + getQuantity(): int
        + calculatePriceTotal(items: List~Item~): int
    }

    class Coupon {
        - type: CouponType
        - amount: int
        - discountRate: int
        + applyDiscountAmount(total: int): int
    }

    class CouponType {
        <<enumeration>>
        FIXED
        PERCENT
        + getDiscountRate(): int
    }

    User "1" -- "1" MembershipLevel
    User "0..1" -- "1" Coupon
    ShoppingCart "1" -- "1" User
    ShoppingCart "1" -- "*" Item
    Coupon -- CouponType
```

## 할인 정책 도메인

```mermaid
classDiagram
    class DiscountPolicy {
        <<interface>>
        + applyDiscount(total: int): int
    }

    class MembershipDiscountPolicy {
        - membershipLevel: MembershipLevel
        + applyDiscount(total: int): int
    }

    class FirstPurchaseDiscountPolicy {
        - isFirstPurchase: boolean
        + applyDiscount(total: int): int
    }

    class CouponDiscountPolicy {
        - coupon: Coupon
        + applyDiscount(total: int): int
    }

    DiscountPolicy <|.. MembershipDiscountPolicy
    DiscountPolicy <|.. FirstPurchaseDiscountPolicy
    DiscountPolicy <|.. CouponDiscountPolicy
```

## 할인 정책 체인 도메인

```mermaid
classDiagram
    class DiscountPolicyChain {
        - policies: List~DiscountPolicy~
        + addPolicy(policy: DiscountPolicy): DiscountPolicyChain
        + applyAll(total: int): int
    }

    DiscountPolicyChain "1" --> "*" DiscountPolicy
```

## 할인 계산 흐름

```mermaid
classDiagram
    class ShoppingCart {
        + calculateTotal(): int
    }

    class User {
        + calculateFinalTotal(total: int): int
    }

    class DiscountPolicyChain {
        + applyAll(total: int): int
    }

    class DiscountPolicy {
        <<interface>>
        + applyDiscount(total: int): int
    }

    ShoppingCart --> User
    User --> DiscountPolicyChain
    DiscountPolicyChain --> DiscountPolicy
```

## 전체 할인 정책 적용 흐름

```mermaid
classDiagram
    class ShoppingCart {
        + calculateTotal(): int
    }

    class Item {
        + calculatePriceTotal(items: List~Item~): int
    }

    class User {
        + calculateFinalTotal(total: int): int
    }

    class DiscountPolicyChain {
        + addPolicy(policy: DiscountPolicy): DiscountPolicyChain
        + applyAll(total: int): int
    }

    class MembershipDiscountPolicy {
        + applyDiscount(total: int): int
    }

    class FirstPurchaseDiscountPolicy {
        + applyDiscount(total: int): int
    }

    class CouponDiscountPolicy {
        + applyDiscount(total: int): int
    }

    ShoppingCart --> Item
    ShoppingCart --> User
    User --> DiscountPolicyChain
    DiscountPolicyChain --> MembershipDiscountPolicy
    DiscountPolicyChain --> FirstPurchaseDiscountPolicy
    DiscountPolicyChain --> CouponDiscountPolicy
```

