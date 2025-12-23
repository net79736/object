package apec.test11.infrastructure.db.order.impl;

import apec.test11.domain.order.model.Order;
import apec.test11.domain.order.repository.OrderRepository;

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

