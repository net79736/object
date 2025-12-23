package apec.test11.interfaces.api.controller.order;

import apec.test11.domain.order.model.Order;
import apec.test11.interfaces.api.dto.order.request.OrderRequest;

// ========== Presentation Layer ==========
// UI/API 처리 인터페이스
public interface IOrderController {
    Order createOrder(OrderRequest request);
}

