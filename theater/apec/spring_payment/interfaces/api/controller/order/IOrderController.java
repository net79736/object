package apec.spring_payment.interfaces.api.controller.order;

import apec.spring_payment.domain.order.model.Order;
import apec.spring_payment.interfaces.api.dto.order.request.OrderRequest;

// ========== Presentation Layer ==========
// UI/API 처리 인터페이스
public interface IOrderController {
    Order createOrder(OrderRequest request);
}

