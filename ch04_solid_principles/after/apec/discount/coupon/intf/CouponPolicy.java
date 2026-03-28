package ch04_solid_principles.after.apec.discount.coupon.intf;

import ch04_solid_principles.after.apec.discount.enums.CouponTypeEnum;
public interface CouponPolicy {
    int applyDiscount(int total);
    CouponTypeEnum getCouponType();
}
