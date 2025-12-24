package apec.discount.coupon.intf;

import apec.discount.enums.CouponTypeEnum;

public interface CouponPolicy {
    int applyDiscount(int total);
    CouponTypeEnum getCouponType();
}
