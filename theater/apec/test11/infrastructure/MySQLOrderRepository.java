package apec.test11.infrastructure;

import apec.test11.domain.Order;
import apec.test11.infrastructure.intf.OrderRepository;

public class MySQLOrderRepository implements OrderRepository {
    public Order save(Order order) {
        // MySQL 저장 로직
        return order;
    }
}
