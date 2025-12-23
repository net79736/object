# report 프로젝트 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 매출 보고서 생성 시스템을 보여줍니다.

```mermaid
classDiagram
    class ReportGenerator {
        - orderRepository: OrderRepository
        + generateSalesReport(startDate: Date, endDate: Date): String
        - formatReport(statistics: SalesCaculator): String
    }

    class SalesCaculator {
        - totalSales: Money
        - totalOrders: int
        - averageOrderValue: Money
        + from(orders: List~Order~): SalesCaculator
        + getTotalSales(): Money
        + getTotalOrders(): int
        + getAverageOrderValue(): Money
    }

    class OrderRepository {
        + findByDateRange(startDate: Date, endDate: Date): List~Order~
    }

    ReportGenerator --> OrderRepository
    ReportGenerator --> SalesCaculator
    OrderRepository --> Order
```

## 도메인 클래스

```mermaid
classDiagram
    class Order {
        - customer: Customer
        - items: List~OrderItem~
        - amount: Money
        - delivery: Delivery
        + getAmount(): Money
    }

    class SalesCaculator {
        - totalSales: Money
        - totalOrders: int
        - averageOrderValue: Money
        + from(orders: List~Order~): SalesCaculator
        + getTotalSales(): Money
        + getTotalOrders(): int
        + getAverageOrderValue(): Money
        - calculateTotalSales(orders: List~Order~): Money
        - calculateAverageOrderValue(totalSales: Money, totalOrders: int): Money
    }

    class Money {
        - amount: BigDecimal
        + ZERO: Money
        + plus(Money): Money
        + divide(divisor: BigDecimal, scale: int, roundingMode: RoundingMode): Money
    }

    SalesCaculator "1" -- "*" Order
    SalesCaculator "1" -- "1" Money
```

## 리포지토리 계층

```mermaid
classDiagram
    class OrderRepository {
        + findByDateRange(startDate: Date, endDate: Date): List~Order~
    }

    OrderRepository --> Order
```

## 서비스 계층

```mermaid
classDiagram
    class ReportGenerator {
        - orderRepository: OrderRepository
        + generateSalesReport(startDate: Date, endDate: Date): String
        - formatReport(statistics: SalesCaculator): String
    }

    ReportGenerator --> OrderRepository
    ReportGenerator --> SalesCaculator
```

## 보고서 생성 흐름

```mermaid
classDiagram
    class ReportGenerator {
        + generateSalesReport(startDate: Date, endDate: Date): String
    }

    class OrderRepository {
        + findByDateRange(startDate: Date, endDate: Date): List~Order~
    }

    class SalesCaculator {
        + from(orders: List~Order~): SalesCaculator
    }

    ReportGenerator --> OrderRepository
    OrderRepository --> Order
    ReportGenerator --> SalesCaculator
    SalesCaculator --> Order
```

