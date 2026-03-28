package ch08_shopping_system.after.apec.payment_order_point.payment.intf;

import ch08_shopping_system.after.apec.payment_order_point.common.User;import ch08_shopping_system.after.apec.payment_order_point.main.PaymentType;
/**
 * 결제 처리를 담당하는 인터페이스
 * 
 * DIP 원칙에 따라 각 결제 수단별 구현체가 이 인터페이스를 구현합니다.
 * PaymentService는 구체적인 결제 처리 로직을 알 필요 없이 이 인터페이스를 통해 결제를 처리합니다.
 * 
 * 할인 정책은 PaymentDiscountPolicy로 분리되어 있어, 결제 처리 책임만 담당합니다.
 * (SRP 원칙: 단일 책임 원칙)
 */
public interface PaymentHandler {
    /**
     * 결제를 처리합니다.
     * 각 구현체는 자신의 결제 수단에 맞는 로직을 구현합니다.
     * 
     * @param user 결제를 수행할 사용자
     * @param amount 결제할 금액
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 오류 등)
     */
    void processPayment(User user, int amount);
    
    /**
     * 이 핸들러가 처리하는 결제 수단 타입을 반환합니다.
     * PaymentService에서 적절한 핸들러를 선택하기 위해 사용됩니다.
     * 
     * @return 처리하는 결제 수단 타입
     */
    PaymentType getPaymentType();
}

