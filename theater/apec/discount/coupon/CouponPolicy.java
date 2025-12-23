package apec.discount.coupon;

public interface CouponPolicy {
    int applyDiscount(int total);
    CouponType getCouponType();
}
