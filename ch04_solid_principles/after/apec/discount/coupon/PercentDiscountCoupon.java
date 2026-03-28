package ch04_solid_principles.after.apec.discount.coupon;

import ch04_solid_principles.after.apec.discount.coupon.intf.CouponPolicy;import ch04_solid_principles.after.apec.discount.enums.CouponTypeEnum;
public class PercentDiscountCoupon implements CouponPolicy {
    private int discountRate;
    private final CouponTypeEnum couponType = CouponTypeEnum.PERCENT;

    public PercentDiscountCoupon(int discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public int applyDiscount(int total) {
        return (int)(total * (1 - discountRate / 100.0));
    }

    @Override
    public CouponTypeEnum getCouponType() {
        return couponType;
    }
}
