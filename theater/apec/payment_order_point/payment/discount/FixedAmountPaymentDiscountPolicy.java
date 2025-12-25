package apec.payment_order_point.payment.discount;

import apec.payment_order_point.main.PaymentType;
import apec.payment_order_point.payment.discount.intf.PaymentDiscountPolicy;

/**
 * 고정 금액 기반 결제 수단별 할인 정책
 * 
 * 결제 수단별로 일정 금액을 할인합니다.
 * 예: 신용카드 사용 시 1,000원 할인
 */
public class FixedAmountPaymentDiscountPolicy implements PaymentDiscountPolicy {
    private final PaymentType paymentType;
    private final int discountAmount; // 할인 금액
    
    public FixedAmountPaymentDiscountPolicy(PaymentType paymentType, int discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException("할인 금액은 0 이상이어야 합니다: " + discountAmount);
        }
        this.paymentType = paymentType;
        this.discountAmount = discountAmount;
    }
    
    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    @Override
    public int applyDiscount(int amount) {
        int discount = calculateDiscountAmount(amount);
        return Math.max(0, amount - discount); // 할인 후 금액이 음수가 되지 않도록
    }
    
    @Override
    public int calculateDiscountAmount(int amount) {
        // 고정 금액 할인은 금액과 상관없이 일정 금액을 할인
        // 단, 원래 금액보다 많이 할인하지 않도록 제한
        return Math.min(discountAmount, amount);
    }
    
    public int getDiscountAmount() {
        return discountAmount;
    }
}

