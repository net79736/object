package apec.test11.domain.order.model;

import apec.test11.domain.order.dto.PaymentInfo;
import apec.test11.domain.order.enums.OrderStatus;
import apec.test11.interfaces.api.dto.order.request.OrderRequest;
import apec.test11.common.Money;

// ========== Domain Layer (최상위) ==========
// 순수 비즈니스 로직, 외부 의존성 없음
public class Order {

    private Long id;
    private Money amount;
    private OrderStatus status;

    public void markAsPaid() {
        this.status = OrderStatus.PAID;
    }
    
    public PaymentInfo getPaymentInfo() {
        return PaymentInfo.of(id, amount, status);
    }
    
    public static Order from(OrderRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'from'");
    }
}

