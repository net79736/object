package ch06_dependency.after.apec.spring_payment.interfaces.api.controller.order;

import ch06_dependency.after.apec.spring_payment.domain.order.model.Order;import ch06_dependency.after.apec.spring_payment.interfaces.api.dto.order.request.OrderRequest;
// ========== Presentation Layer ==========
// UI/API 처리 인터페이스
public interface IOrderController {
    Order createOrder(OrderRequest request);
}

