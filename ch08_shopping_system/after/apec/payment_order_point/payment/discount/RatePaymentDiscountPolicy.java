package ch08_shopping_system.after.apec.payment_order_point.payment.discount;

import ch08_shopping_system.after.apec.payment_order_point.main.PaymentType;import ch08_shopping_system.after.apec.payment_order_point.payment.discount.intf.PaymentDiscountPolicy;
/**
 * 할인율 기반 결제 수단별 할인 정책
 * 
 * 결제 수단별로 일정 비율의 할인을 적용합니다.
 * 예: 신용카드 10% 할인, 계좌이체 5% 할인
 */
public class RatePaymentDiscountPolicy implements PaymentDiscountPolicy {
    private final PaymentType paymentType;
    private final int discountRate; // 할인율 (퍼센트)
    
    public RatePaymentDiscountPolicy(PaymentType paymentType, int discountRate) {
        if (discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("할인율은 0~100 사이여야 합니다: " + discountRate);
        }
        this.paymentType = paymentType;
        this.discountRate = discountRate;
    }
    
    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    @Override
    public int applyDiscount(int amount) {
        int discountAmount = calculateDiscountAmount(amount);
        return amount - discountAmount;
    }
    
    @Override
    public int calculateDiscountAmount(int amount) {
        return (amount * discountRate) / 100;
    }
    
    public int getDiscountRate() {
        return discountRate;
    }
}

