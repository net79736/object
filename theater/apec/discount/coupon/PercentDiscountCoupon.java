package apec.discount.coupon;

import apec.discount.coupon.intf.CouponPolicy;
import apec.discount.enums.CouponType;

public class PercentDiscountCoupon implements CouponPolicy {
    private int discountRate;
    private final CouponType couponType = CouponType.PERCENT;

    public PercentDiscountCoupon(int discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public int applyDiscount(int total) {
        return (int)(total * (1 - discountRate / 100.0));
    }

    @Override
    public CouponType getCouponType() {
        return couponType;
    }
}
