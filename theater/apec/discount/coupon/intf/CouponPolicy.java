package apec.discount.coupon.intf;

import apec.discount.enums.CouponType;

public interface CouponPolicy {
    int applyDiscount(int total);
    CouponType getCouponType();
}
