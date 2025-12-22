package apec.test11.service;

import apec.test11.domain.Order;
import apec.test11.dto.OrderRequest;
import apec.test11.infrastructure.MySQLOrderRepository;
import apec.test11.infrastructure.StripePaymentGateway;
import apec.test11.infrastructure.intf.OrderRepository;
import apec.test11.infrastructure.intf.PaymentGateway;

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
