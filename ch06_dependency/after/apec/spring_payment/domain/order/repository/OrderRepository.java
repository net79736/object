package ch06_dependency.after.apec.spring_payment.domain.order.repository;

import ch06_dependency.after.apec.spring_payment.domain.order.model.Order;
public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
}

