package test.claude6_3;

import test.claude6_3.enums.MembershipLevel;
import test.claude6_3.policy.CouponDiscountPolicy;
import test.claude6_3.policy.DiscountPolicyChain;
import test.claude6_3.policy.FirstPurchaseDiscountPolicy;
import test.claude6_3.policy.MembershipDiscountPolicy;

public class User {
    private MembershipLevel membershipLevel; // 사용자 등급
    private boolean isFirstPurchase; // 첫 구매 여부
    private Coupon coupon; // 쿠폰

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }
    
    public boolean isFirstPurchase() {
        return isFirstPurchase;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public boolean hasCoupon() {
        return coupon != null;
    }

    /**
     * 사용자의 모든 할인 정책을 적용하여 최종 금액을 계산합니다.
     * 묻지 말고 시켜라 원칙: User가 자신의 할인을 계산합니다.
     * 
     * @param total 초기 총 금액
     * @return 모든 할인 적용 후 금액
     */
    public int calculateFinalTotal(int total) {
        DiscountPolicyChain chain = new DiscountPolicyChain()
            .addPolicy(new MembershipDiscountPolicy(membershipLevel))
            .addPolicy(new FirstPurchaseDiscountPolicy(isFirstPurchase))
            .addPolicy(new CouponDiscountPolicy(coupon));
        
        return chain.applyAll(total);
    }

    // 하위 호환성을 위한 메서드들 (기존 코드와의 호환성 유지)
    /**
     * 사용자 등급별 할인 계산
     * @param total
     * @return 할인 후 금액
     */
    public int calculateDiscountedTotal(int total) {
        return new MembershipDiscountPolicy(membershipLevel).applyDiscount(total);
    }

    public int calculateFirstPurchaseDiscountedTotal(int total) {
        return new FirstPurchaseDiscountPolicy(isFirstPurchase).applyDiscount(total);
    }

    public int applyCouponDiscount(int total) {
        return new CouponDiscountPolicy(coupon).applyDiscount(total);
    }
}
