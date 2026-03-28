package ch04_solid_principles.after.apec.discount.discount;

import ch04_solid_principles.after.apec.discount.discount.intf.DiscountPolicy;
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
