package apec.test11.infrastructure.intf;

import apec.test11.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
