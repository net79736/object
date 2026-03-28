package ch04_solid_principles.after.apec.order;

import ch04_solid_principles.after.apec.order.payment.factory.PaymentGatewayFactory;import ch04_solid_principles.after.apec.order.payment.intf.PaymentGateway;
/**
 * 주문에 대한 결제를 처리하는 클래스
 * 
 * 변하지 않는 부분: 결제를 처리한다는 행위 자체
 * - 주문을 받아서 결제를 처리하고 상태를 업데이트하는 책임
 * 
 * 변하는 부분: 각 결제 수단의 구체적인 처리 방법
 * - PaymentGateway 구현체들이 담당 (Strategy 패턴)
 * - PaymentGatewayFactory가 결제 수단 선택 담당
 * 
 * OCP 원칙 준수:
 * - 새로운 결제 수단 추가 시 이 클래스를 수정할 필요 없음
 * - PaymentGatewayFactory와 PaymentGateway 인터페이스만 확장하면 됨
 */
public class PaymentProcessor {
    
    /**
     * 주문에 대한 결제를 처리합니다.
     * 
     * 변하지 않는 부분: 결제 처리 흐름 자체
     * 1. 결제 수단에 맞는 Gateway 선택
     * 2. 결제 실행
     * 3. 주문 상태 업데이트
     * 
     * @param order 결제할 주문
     * @throws IllegalArgumentException 지원하지 않는 결제 수단인 경우
     */
    public void processPayment(Order order) {
        // 결제 수단 타입 반환
        PaymentTypeEnum paymentTypeEnum = order.paymentType(); 
        
        // 변하는 부분: 결제 수단 선택 (Factory에 위임)
        PaymentGateway paymentGateway = PaymentGatewayFactory.create(paymentTypeEnum);
        
        // 변하지 않는 부분: 결제 처리 행위 자체
        paymentGateway.requestPayment(order, order.getPaymentInfo(), order.getAmount());
        
        // 변하지 않는 부분: 주문 상태 업데이트
        order.updateOrderStatusWithPaymentDate(OrderStatus.PAID);
    }
}
