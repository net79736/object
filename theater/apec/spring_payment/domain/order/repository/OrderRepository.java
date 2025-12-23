package apec.spring_payment.domain.order.repository;

import apec.spring_payment.domain.order.model.Order;

public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
}

