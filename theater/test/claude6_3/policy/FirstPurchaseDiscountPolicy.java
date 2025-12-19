package test.claude6_3.policy;

import test.claude6_3.policy.interfaces.DiscountPolicy;

/**
 * 첫 구매 할인 정책
 */
public class FirstPurchaseDiscountPolicy implements DiscountPolicy {
    private final boolean isFirstPurchase;

    public FirstPurchaseDiscountPolicy(boolean isFirstPurchase) {
        this.isFirstPurchase = isFirstPurchase;
    }

    @Override
    public int applyDiscount(int total) {
        if (isFirstPurchase) {
            return (int)(total * 0.9); // 10% 추가 할인
        }
        return total;
    }
}

