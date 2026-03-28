package ch04_solid_principles.after.apec.order_factory;

import java.util.List;

import ch04_solid_principles.after.apec.order_factory.factory.OrderFactory;import common.Money;

public class OrderService {

    private CustomerRepository customerRepository;
    private OrderFactory orderFactory;

    public OrderService(CustomerRepository customerRepository, OrderFactory orderFactory) {
        this.customerRepository = customerRepository;
        this.orderFactory = orderFactory;
    }

    public Order createOrder(Long customerId, List<Long> productIds) {
        // 주문 조회
        Order order = orderFactory.createOrder(customerId, productIds);
        
        // 재고 확인 및 차감
        order.reserveStock();
        
        return order;
    }
    
    public Money calculateOrderPrice(Order order) {
        return order.calculateTotalPrice();
    }
}
