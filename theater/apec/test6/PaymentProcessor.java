package apec.test6;

import apec.test6.payment.PaymentGatewayFactory;
import apec.test6.payment.intf.PaymentGateway;

/**
 * 주문에 대한 결제를 처리하는 클래스
 * PaymentType에 따라 적절한 PaymentGateway를 선택하여 결제를 처리합니다.
 * 
 * OCP 원칙 준수:
 * - 새로운 결제 수단 추가 시 이 클래스를 수정할 필요 없음
 * - PaymentGatewayFactory와 PaymentGateway 인터페이스만 확장하면 됨
 */
public class PaymentProcessor {
    
    /**
     * 주문에 대한 결제를 처리합니다.
     * 
     * @param order 결제할 주문
     * @throws IllegalArgumentException 지원하지 않는 결제 수단인 경우
     */
    public void processPayment(Order order) {
        PaymentType paymentType = order.getPaymentType();
        
        // PaymentType에 따라 적절한 PaymentGateway 구현체를 생성
        PaymentGateway paymentGateway = PaymentGatewayFactory.create(paymentType);
        
        // 결제 처리
        paymentGateway.pay(order);
        
        // 주문 상태 업데이트
        order.setPaymentStatus(OrderStatus.PAID);
    }
}
