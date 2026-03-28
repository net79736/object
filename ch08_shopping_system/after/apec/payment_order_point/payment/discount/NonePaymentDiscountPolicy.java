package ch08_shopping_system.after.apec.payment_order_point.payment.discount;

import ch08_shopping_system.after.apec.payment_order_point.main.PaymentType;import ch08_shopping_system.after.apec.payment_order_point.payment.discount.intf.PaymentDiscountPolicy;
/**
 * 할인 없는 결제 수단별 할인 정책
 * 
 * 할인을 적용하지 않는 결제 수단에 사용됩니다.
 * 예: 포인트 결제는 일반적으로 할인이 없습니다.
 */
public class NonePaymentDiscountPolicy implements PaymentDiscountPolicy {
    private final PaymentType paymentType;
    
    public NonePaymentDiscountPolicy(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    @Override
    public int applyDiscount(int amount) {
        return amount; // 할인 없음
    }
    
    @Override
    public int calculateDiscountAmount(int amount) {
        return 0; // 할인 금액 없음
    }
}

