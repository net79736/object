# spring_payment 프로젝트 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 계층형 아키텍처를 사용하는 결제 시스템을 보여줍니다.

```mermaid
classDiagram
    class OrderController {
        - createOrderUseCase: CreateOrderUseCase
        + createOrder(request: OrderRequest): Order
    }

    class CreateOrderUseCase {
        - orderRepository: OrderRepository
        - paymentGateway: PaymentGateway
        + execute(request: OrderRequest): Order
    }

    class Order {
        - id: Long
        - amount: Money
        - status: OrderStatus
        + markAsPaid(): void
        + getPaymentInfo(): PaymentInfo
        + from(request: OrderRequest): Order
    }

    OrderController --> CreateOrderUseCase
    CreateOrderUseCase --> OrderRepository
    CreateOrderUseCase --> PaymentGateway
    CreateOrderUseCase --> Order
```

## 계층별 구조

### 인터페이스 계층 (Presentation Layer)

```mermaid
classDiagram
    class IOrderController {
        <<interface>>
        + createOrder(request: OrderRequest): Order
    }

    class OrderController {
        - createOrderUseCase: CreateOrderUseCase
        + createOrder(request: OrderRequest): Order
    }

    class OrderRequest {
        - amount: BigDecimal
        - paymentInfo: PaymentInfo
    }

    class PaymentResponse {
        - success: boolean
        - message: String
        + isSuccess(): boolean
    }

    IOrderController <|.. OrderController
    OrderController --> OrderRequest
    OrderController --> PaymentResponse
```

### 애플리케이션 계층 (Application Layer)

```mermaid
classDiagram
    class CreateOrderUseCase {
        - orderRepository: OrderRepository
        - paymentGateway: PaymentGateway
        + execute(request: OrderRequest): Order
    }

    CreateOrderUseCase --> OrderRepository
    CreateOrderUseCase --> PaymentGateway
    CreateOrderUseCase --> Order
```

### 도메인 계층 (Domain Layer)

```mermaid
classDiagram
    class Order {
        - id: Long
        - amount: Money
        - status: OrderStatus
        + markAsPaid(): void
        + getPaymentInfo(): PaymentInfo
        + from(request: OrderRequest): Order
    }

    class OrderStatus {
        <<enumeration>>
        PENDING
        PAID
        CANCELLED
    }

    class PaymentInfo {
        - orderId: Long
        - amount: Money
        - status: OrderStatus
        + of(orderId: Long, amount: Money, status: OrderStatus): PaymentInfo
    }

    class Money {
        - amount: BigDecimal
        + ZERO: Money
        + wons(long): Money
    }

    Order -- OrderStatus
    Order "1" -- "1" Money
    PaymentInfo "1" -- "1" Money
    PaymentInfo -- OrderStatus
```

### 인프라스트럭처 계층 (Infrastructure Layer)

```mermaid
classDiagram
    class OrderRepository {
        <<interface>>
        + save(order: Order): Order
        + findById(id: Long): Order
    }

    class MongoOrderRepository {
        + save(order: Order): Order
        + findById(id: Long): Order
    }

    class MySQLOrderRepository {
        + save(order: Order): Order
        + findById(id: Long): Order
    }

    class PaymentGateway {
        <<interface>>
        + process(paymentInfo: PaymentInfo): PaymentResponse
    }

    class StripePaymentGateway {
        + process(paymentInfo: PaymentInfo): PaymentResponse
    }

    class PayPalPaymentGateway {
        + process(paymentInfo: PaymentInfo): PaymentResponse
    }

    OrderRepository <|.. MongoOrderRepository
    OrderRepository <|.. MySQLOrderRepository
    PaymentGateway <|.. StripePaymentGateway
    PaymentGateway <|.. PayPalPaymentGateway
```

### 설정 계층 (Config Layer)

```mermaid
classDiagram
    class DataSource {
        + getConnection(): Connection
    }

    class MongoClient {
        + getDatabase(): MongoDatabase
    }

    class PayPalClient {
        + createPayment(): Payment
    }

    DataSource --> Connection
    MongoClient --> MongoDatabase
    PayPalClient --> Payment
```

## 주문 생성 흐름

```mermaid
classDiagram
    class OrderController {
        + createOrder(request: OrderRequest): Order
    }

    class CreateOrderUseCase {
        + execute(request: OrderRequest): Order
    }

    class OrderRepository {
        + save(order: Order): Order
    }

    class PaymentGateway {
        + process(paymentInfo: PaymentInfo): PaymentResponse
    }

    class Order {
        + markAsPaid(): void
    }

    OrderController --> CreateOrderUseCase
    CreateOrderUseCase --> OrderRepository
    CreateOrderUseCase --> PaymentGateway
    CreateOrderUseCase --> Order
    OrderRepository --> Order
```

