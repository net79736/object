package apec.payment_order_point.payment.discount.intf;

import apec.payment_order_point.main.PaymentType;

/**
 * 결제 수단별 할인 정책 인터페이스
 * 
 * Strategy 패턴을 사용하여 결제 수단별로 다양한 할인 정책을 적용할 수 있습니다.
 * 이벤트별로 다른 할인 정책을 적용할 수 있도록 설계되었습니다.
 * 
 * DIP 원칙에 따라 구체적인 할인 정책 구현체가 아닌 이 인터페이스에 의존합니다.
 */
public interface PaymentDiscountPolicy {
    /**
     * 이 정책이 적용되는 결제 수단 타입을 반환합니다.
     * 
     * @return 결제 수단 타입
     */
    PaymentType getPaymentType();
    
    /**
     * 금액에 할인을 적용합니다.
     * 
     * @param amount 할인 전 금액
     * @return 할인 적용 후 금액
     */
    int applyDiscount(int amount);
    
    /**
     * 할인 금액을 계산합니다.
     * 
     * @param amount 할인 전 금액
     * @return 할인 금액
     */
    int calculateDiscountAmount(int amount);
}

