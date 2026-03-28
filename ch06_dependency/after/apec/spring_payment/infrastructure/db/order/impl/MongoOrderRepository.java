package ch06_dependency.after.apec.spring_payment.infrastructure.db.order.impl;

import ch06_dependency.after.apec.spring_payment.domain.order.model.Order;import ch06_dependency.after.apec.spring_payment.domain.order.repository.OrderRepository;
public class MongoOrderRepository implements OrderRepository {
    
    @Override
    public Order findById(Long id) {
        // MongoDB 조회 로직
        return new Order();
    }

    @Override
    public Order save(Order order) {
        // MongoDB 저장 로직
        return order;
    }
}

