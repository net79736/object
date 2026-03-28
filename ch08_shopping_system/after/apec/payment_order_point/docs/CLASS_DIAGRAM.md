# payment_order_point 프로젝트 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 포인트 결제를 포함한 복합 결제 시스템을 보여줍니다.

```mermaid
classDiagram
    class PaymentService {
        - paymentHandlers: List~PaymentHandler~
        + processPayment(order: Order, totalAmount: int): void
        + calculateFinalAmount(originalAmount: int, paymentType: PaymentType): int
        - executePaymentPlan(user: User, paymentPlan: PaymentPlan): void
        - findHandler(paymentType: PaymentType): PaymentHandler
    }

    class Order {
        - items: List~OrderItem~
        - user: User
        - paymentType: PaymentType
        - pointAmount: Integer
        - validators: List~PaymentPlanValidator~
        + createPaymentPlan(totalAmount: int): PaymentPlan
        + sendOrderCompletedNotification(message: String): void
    }

    PaymentService --> Order
    PaymentService --> PaymentPlan
    PaymentService --> PaymentHandler
    Order --> PaymentPlan
```

## 도메인 클래스

```mermaid
classDiagram
    class User {
        - membershipLevel: MembershipLevel
        - point: Point
        + calculateFinalTotal(total: int): int
        + sendNotification(message: String): void
        + decreasePoint(point: int): void
        + increasePoint(point: int): void
        + checkMaxPoint(point: int): void
    }

    class MembershipLevel {
        <<enumeration>>
        VIP
        GOLD
        SILVER
        NORMAL
    }

    class Order {
        - items: List~OrderItem~
        - user: User
        - paymentType: PaymentType
        - pointAmount: Integer
        - validators: List~PaymentPlanValidator~
        + createPaymentPlan(totalAmount: int): PaymentPlan
        + sendOrderCompletedNotification(message: String): void
    }

    class OrderItem {
        - product: Product
        - quantity: int
    }

    class Product {
        - id: Long
        - name: String
        - price: int
        - stock: Stock
    }

    class Stock {
        - quantity: int
    }

    User "1" -- "1" MembershipLevel
    User "1" -- "1" Point
    Order "1" -- "1" User
    Order "1" -- "*" OrderItem
    OrderItem "1" -- "1" Product
    Product "1" -- "1" Stock
```

## 결제 계획 도메인

```mermaid
classDiagram
    class PaymentPlan {
        - paymentItems: List~PaymentItem~
        + single(paymentType: PaymentType, amount: int): PaymentPlan
        + mixed(pointAmount: int, remainingPaymentType: PaymentType, remainingAmount: int): PaymentPlan
        + getPaymentItems(): List~PaymentItem~
    }

    class PaymentItem {
        - paymentType: PaymentType
        - amount: int
    }

    class PaymentType {
        <<enumeration>>
        POINT
        CREDIT_CARD
        ACCOUNT_TRANSFER
    }

    PaymentPlan "1" -- "*" PaymentItem
    PaymentItem -- PaymentType
```

## 결제 핸들러 도메인

```mermaid
classDiagram
    class PaymentHandler {
        <<interface>>
        + processPayment(user: User, amount: int): void
        + getPaymentType(): PaymentType
        + getDiscountRate(): int
    }

    class PointPaymentHandler {
        + processPayment(user: User, amount: int): void
        + getPaymentType(): PaymentType
        + getDiscountRate(): int
    }

    class CreditCardPaymentHandler {
        + processPayment(user: User, amount: int): void
        + getPaymentType(): PaymentType
        + getDiscountRate(): int
    }

    class AccountTransferPaymentHandler {
        + processPayment(user: User, amount: int): void
        + getPaymentType(): PaymentType
        + getDiscountRate(): int
    }

    PaymentHandler <|.. PointPaymentHandler
    PaymentHandler <|.. CreditCardPaymentHandler
    PaymentHandler <|.. AccountTransferPaymentHandler
```

## 결제 계획 검증 도메인

```mermaid
classDiagram
    class PaymentPlanValidator {
        <<interface>>
        + validate(pointAmount: Integer, totalAmount: int, paymentType: PaymentType, user: User): void
    }

    class PointAmountValidator {
        + validate(pointAmount: Integer, totalAmount: int, paymentType: PaymentType, user: User): void
    }

    class PointBalanceValidator {
        + validate(pointAmount: Integer, totalAmount: int, paymentType: PaymentType, user: User): void
    }

    class PaymentTypeValidator {
        + validate(pointAmount: Integer, totalAmount: int, paymentType: PaymentType, user: User): void
    }

    PaymentPlanValidator <|.. PointAmountValidator
    PaymentPlanValidator <|.. PointBalanceValidator
    PaymentPlanValidator <|.. PaymentTypeValidator
```

## 포인트 도메인

```mermaid
classDiagram
    class Point {
        - point: int
        + getPoint(): int
        + increase(point: int): void
        + decrease(point: int): void
        + checkMaxPoint(point: int): void
    }

    class PointService {
        + usePoint(user: User, point: int): void
        + chargePoint(user: User, point: int): void
    }

    class PointConstants {
        <<utility>>
        + MAX_POINT: int
    }

    PointService ..> User
    PointService ..> Point
    Point ..> PointConstants
```

## 할인 정책 도메인

```mermaid
classDiagram
    class DiscountPolicy {
        <<interface>>
        + applyDiscount(total: int): int
    }

    class GradeDiscountPolicy {
        - membershipLevel: MembershipLevel
        + applyDiscount(total: int): int
    }

    class CouponDiscountPolicy {
        - coupon: Coupon
        + applyDiscount(total: int): int
    }

    DiscountPolicy <|.. GradeDiscountPolicy
    DiscountPolicy <|.. CouponDiscountPolicy
```

## 쿠폰 도메인

```mermaid
classDiagram
    class Coupon {
        - couponType: CouponType
        - discountAmount: int
        - discountRate: double
    }

    class CouponType {
        <<enumeration>>
        FIXED
        PERCENT
    }

    Coupon -- CouponType
```

## 알림 도메인

```mermaid
classDiagram
    class NotificationService {
        + sendNotification(user: User, message: String): void
    }

    class NotificationSender {
        <<interface>>
        + send(user: User, message: String): void
        + isEnabled(settings: NotificationSettings): boolean
    }

    class NotificationSettings {
        - emailEnabled: boolean
        - smsEnabled: boolean
    }

    class EmailSender {
        + send(user: User, message: String): void
        + isEnabled(settings: NotificationSettings): boolean
    }

    class SmsSender {
        + send(user: User, message: String): void
        + isEnabled(settings: NotificationSettings): boolean
    }

    NotificationService --> NotificationSender
    NotificationSender <|.. EmailSender
    NotificationSender <|.. SmsSender
    NotificationSender ..> NotificationSettings
```

## 리포트 도메인

```mermaid
classDiagram
    class ReportGenerator {
        + generateReport(data: List~SalesData~): String
    }

    ReportGenerator ..> SalesData
```

## 서비스 계층

```mermaid
classDiagram
    class PaymentService {
        - paymentHandlers: List~PaymentHandler~
        + processPayment(order: Order, totalAmount: int): void
        + calculateFinalAmount(originalAmount: int, paymentType: PaymentType): int
    }

    class OrderService {
        + createOrder(items: List~OrderItem~, user: User, paymentType: PaymentType): Order
        + processOrder(order: Order): void
    }

    class PointService {
        + usePoint(user: User, point: int): void
        + chargePoint(user: User, point: int): void
    }

    PaymentService --> PaymentHandler
    OrderService --> Order
    PointService --> User
```

## 결제 처리 흐름

```mermaid
classDiagram
    class Order {
        + createPaymentPlan(totalAmount: int): PaymentPlan
    }

    class PaymentPlan {
        + getPaymentItems(): List~PaymentItem~
    }

    class PaymentService {
        + processPayment(order: Order, totalAmount: int): void
        - executePaymentPlan(user: User, paymentPlan: PaymentPlan): void
    }

    class PaymentHandler {
        <<interface>>
        + processPayment(user: User, amount: int): void
    }

    Order --> PaymentPlan
    PaymentService --> Order
    PaymentService --> PaymentPlan
    PaymentService --> PaymentHandler
```

