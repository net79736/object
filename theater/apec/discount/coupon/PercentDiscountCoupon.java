package apec.discount.coupon;

import apec.discount.coupon.intf.CouponPolicy;
import apec.discount.enums.CouponTypeEnum;

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
