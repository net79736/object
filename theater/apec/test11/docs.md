```java
// 전형적인 3-Layer Architecture
// Presentation Layer
public class OrderController {
    private OrderService orderService = new OrderService();
    
    public ResponseEntity<Order> createOrder(OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}

// Business Layer
public class OrderService {
    private OrderRepository orderRepository = new MySQLOrderRepository();
    private PaymentGateway paymentGateway = new StripePaymentGateway();
    
    public Order createOrder(OrderRequest request) {
        Order order = Order.from(request);
        order = orderRepository.save(order);
        paymentGateway.processPayment(order.getPaymentInfo());
        return order;
    }
}

// Data Layer
public class MySQLOrderRepository {
    public Order save(Order order) {
        // MySQL 저장 로직
    }
}

public class StripePaymentGateway {
    public void processPayment(PaymentInfo info) {
        // Stripe API 호출
    }
}
```
질문:

이 계층 구조의 의존성 방향을 그림으로 그리시오
각 계층을 독립적으로 테스트하기 어려운 이유를 설명하시오
DIP를 적용하여 "Clean Architecture" 스타일로 개선하시오

----

// OrderService 테스트 시도
```java
@Test
void testCreateOrder() {
    OrderService service = new OrderService();
    // ⬆️ 여기서 이미 MySQLOrderRepository와 StripePaymentGateway 생성!
    
    // 문제점:
    // 1. 실제 MySQL 필요 (통합 테스트가 되어버림)
    // 2. Stripe API 호출 발생 (비용 발생!)
    // 3. Mock으로 대체 불가능
    // 4. 테스트가 느리고 불안정
}

// OrderController 테스트 시도
@Test
void testController() {
    OrderController controller = new OrderController();
    // ⬆️ OrderService 생성 → Repository, Gateway 생성
    // ⇒ Controller 테스트인데 전체 스택 필요!
}
```

Clean Architecture 스타일 리팩토링:

