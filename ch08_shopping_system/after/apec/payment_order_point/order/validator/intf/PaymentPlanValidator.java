package ch08_shopping_system.after.apec.payment_order_point.order.validator.intf;

import ch08_shopping_system.after.apec.payment_order_point.common.User;import ch08_shopping_system.after.apec.payment_order_point.main.PaymentType;
/**
 * 결제 계획 검증 인터페이스
 * 
 * DIP 원칙: Order는 구체적인 검증 로직이 아닌 이 인터페이스에 의존합니다.
 * OCP 원칙: 새로운 검증 규칙 추가 시 이 인터페이스를 구현하는 새로운 Validator만 추가하면 됩니다.
 */
public interface PaymentPlanValidator {
    /**
     * 결제 계획을 검증합니다.
     * 
     * @param pointAmount 포인트 사용 금액 (null이면 포인트 미사용)
     * @param totalAmount 총 결제 금액
     * @param paymentType 나머지 금액 결제 수단
     * @param user 사용자
     * @throws RuntimeException 검증 실패 시
     */
    void validate(Integer pointAmount, int totalAmount, PaymentType paymentType, User user);
}

