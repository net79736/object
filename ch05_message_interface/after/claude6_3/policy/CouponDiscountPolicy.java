package ch05_message_interface.after.claude6_3.policy;

import ch05_message_interface.after.claude6_3.Coupon;import ch05_message_interface.after.claude6_3.policy.interfaces.DiscountPolicy;
/**
 * 쿠폰 할인 정책
 */
public class CouponDiscountPolicy implements DiscountPolicy {
    private final Coupon coupon;

    public CouponDiscountPolicy(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public int applyDiscount(int total) {
        if (coupon != null) {
            return coupon.applyDiscountAmount(total);
        }
        return total;
    }
}

