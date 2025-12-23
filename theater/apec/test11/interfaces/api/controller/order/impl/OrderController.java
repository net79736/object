package apec.test11.interfaces.api.controller.order.impl;

import apec.test11.application.order.CreateOrderUseCase;
import apec.test11.domain.order.model.Order;
import apec.test11.interfaces.api.controller.order.IOrderController;
import apec.test11.interfaces.api.dto.order.request.OrderRequest;

// ========== Presentation Layer ==========
// UI/API 처리 구현체
public class OrderController implements IOrderController {
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }
    
    @Override
    public Order createOrder(OrderRequest request) {
        return createOrderUseCase.execute(request);
    }
}

