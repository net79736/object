package apec.discount.coupon;

import apec.discount.coupon.intf.CouponPolicy;
import apec.discount.enums.CouponTypeEnum;

public class FixedDiscountCoupon implements CouponPolicy {
    private int amount;
    private final CouponTypeEnum couponType = CouponTypeEnum.FIXED;

    public FixedDiscountCoupon(int amount) {
        this.amount = amount;
    }

    @Override
    public int applyDiscount(int total) {
        return total - amount;
    }

    @Override
    public CouponTypeEnum getCouponType() { 
        return couponType;
    }
}
