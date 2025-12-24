package apec.discount.coupon;

import apec.discount.coupon.intf.CouponPolicy;
import apec.discount.enums.CouponType;

public class FixedDiscountCoupon implements CouponPolicy {
    private int amount;
    private final CouponType couponType = CouponType.FIXED;

    public FixedDiscountCoupon(int amount) {
        this.amount = amount;
    }

    @Override
    public int applyDiscount(int total) {
        return total - amount;
    }

    @Override
    public CouponType getCouponType() { 
        return couponType;
    }
}
