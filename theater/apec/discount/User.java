package apec.discount;

import java.util.ArrayList;
import java.util.List;
import apec.discount.coupon.Coupon;
import apec.discount.discount.CouponDiscountPolicy;
import apec.discount.discount.FirstPurchaseDiscountPolicy;
import apec.discount.discount.GradeDiscountPolicy;
import apec.discount.discount.intf.DiscountPolicy;

/**
 * 사용자 클래스
 * 사용자의 멤버십 등급, 첫 구매 여부, 쿠폰 정보를 관리합니다.
 * "묻지 말고 시켜라" 원칙: 사용자가 자신의 할인을 계산합니다.
 */
public class User {
    private MembershipLevel membershipLevel;
    private boolean isFirstPurchase;
    private Coupon coupon;

    public User(MembershipLevel membershipLevel, boolean isFirstPurchase, Coupon coupon) {
        this.membershipLevel = membershipLevel;
        this.isFirstPurchase = isFirstPurchase;
        this.coupon = coupon;
    }

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
        List<DiscountPolicy> policies = createDiscountPolicies();
        
        int finalTotal = total;
        for (DiscountPolicy policy : policies) {
            finalTotal = policy.applyDiscount(finalTotal);
        }
        
        return finalTotal;
    }

    /**
     * 사용자의 할인 정책 목록을 생성합니다.
     * 등급 할인 -> 첫 구매 할인 -> 쿠폰 할인 순서로 적용됩니다.
     * 
     * @return 할인 정책 목록
     */
    private List<DiscountPolicy> createDiscountPolicies() {
        List<DiscountPolicy> policies = new ArrayList<>();
        
        // 등급별 할인
        policies.add(new GradeDiscountPolicy(membershipLevel));
        
        // 첫 구매 할인
        policies.add(new FirstPurchaseDiscountPolicy(isFirstPurchase));
        
        // 쿠폰 할인
        if (hasCoupon()) {
            policies.add(new CouponDiscountPolicy(coupon));
        }
        
        return policies;
    }
}
