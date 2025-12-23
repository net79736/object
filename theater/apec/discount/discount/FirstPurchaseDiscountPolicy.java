package apec.test3.discount;

import apec.test3.discount.intf.DiscountPolicy;

public class FirstPurchaseDiscountPolicy implements DiscountPolicy {
    private boolean isFirstPurchase;

    public FirstPurchaseDiscountPolicy(boolean isFirstPurchase) {
        this.isFirstPurchase = isFirstPurchase;
    }

    @Override
    public int applyDiscount(int total) {
        return isFirstPurchase ? (int)(total * 0.9) : total;
    }
}
