package apec.test11.domain.order.repository;

import apec.test11.domain.order.model.Order;

public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
}

