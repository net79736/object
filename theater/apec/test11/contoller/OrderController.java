package apec.test11.contoller;

import apec.test11.domain.Order;
import apec.test11.dto.OrderRequest;
import apec.test11.service.OrderService;

public class OrderController {
    private OrderService orderService = new OrderService();    

    public Order createOrder(OrderRequest request) {
        return orderService.createOrder(request);
    }
}
