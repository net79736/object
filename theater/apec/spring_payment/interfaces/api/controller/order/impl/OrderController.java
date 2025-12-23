package apec.spring_payment.interfaces.api.controller.order.impl;

import apec.spring_payment.application.order.CreateOrderUseCase;
import apec.spring_payment.domain.order.model.Order;
import apec.spring_payment.interfaces.api.controller.order.IOrderController;
import apec.spring_payment.interfaces.api.dto.order.request.OrderRequest;

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

