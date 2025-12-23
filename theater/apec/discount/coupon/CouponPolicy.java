package apec.test3.coupon;

public interface CouponPolicy {
    int applyDiscount(int total);
    CouponType getCouponType();
}
