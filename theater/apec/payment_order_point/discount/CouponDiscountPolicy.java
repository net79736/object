package apec.test5.discount;

import apec.test5.coupon.Coupon;
import apec.test5.discount.intf.DiscountPolicy;

/**
 * 쿠폰 할인 정책
 * 
 * 쿠폰이 있는 경우 쿠폰의 할인을 적용합니다.
 * "묻지 말고 시켜라" 원칙: 쿠폰이 자신의 할인을 계산합니다.
 */
public class CouponDiscountPolicy implements DiscountPolicy {
    private final Coupon coupon;

    public CouponDiscountPolicy(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public int applyDiscount(int total) {
        if (coupon == null) {
            return total;
        }
        
        // 쿠폰의 applyDiscountAmount 메서드를 사용하여 할인 적용
        // "묻지 말고 시켜라" 원칙: 쿠폰이 자신의 할인을 계산합니다.
        return coupon.applyDiscountAmount(total);
    }
    
    public Coupon getCoupon() {
        return coupon;
    }

    public boolean hasCoupon() {
        return coupon != null;
    }
}

