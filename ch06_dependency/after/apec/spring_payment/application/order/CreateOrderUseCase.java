package ch06_dependency.after.apec.spring_payment.application.order;

import ch06_dependency.after.apec.spring_payment.domain.order.model.Order;import ch06_dependency.after.apec.spring_payment.domain.order.repository.OrderRepository;import ch06_dependency.after.apec.spring_payment.infrastructure.gateway.payment.intf.PaymentGateway;import ch06_dependency.after.apec.spring_payment.interfaces.api.dto.order.request.OrderRequest;import ch06_dependency.after.apec.spring_payment.interfaces.api.dto.order.response.PaymentResponse;
// ========== Application Layer ==========
// Use Case 구현, 인터페이스 정의
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final PaymentGateway paymentGateway;

        // 생성자 주입
    public CreateOrderUseCase(OrderRepository orderRepository,
                              PaymentGateway paymentGateway) {
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
    }

    public Order execute(OrderRequest request) {
        Order order = Order.from(request);
        order = orderRepository.save(order);
        
        PaymentResponse result = paymentGateway.process(order.getPaymentInfo());
        
        if (result.isSuccess()) {
            order.markAsPaid();
            orderRepository.save(order);
        }
        
        return order;
    }
}

