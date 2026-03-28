package ch05_message_interface.after.claude6_3;

import ch05_message_interface.after.claude6_3.enums.MembershipLevel;import ch05_message_interface.after.claude6_3.policy.CouponDiscountPolicy;import ch05_message_interface.after.claude6_3.policy.FirstPurchaseDiscountPolicy;import ch05_message_interface.after.claude6_3.policy.MembershipDiscountPolicy;import ch05_message_interface.after.claude6_3.policy.chain.DiscountPolicyChain;
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
}
