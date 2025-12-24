package apec.discount;

import java.util.ArrayList;
import java.util.List;

import apec.discount.discount.CouponDiscountPolicy;
import apec.discount.discount.FirstPurchaseDiscountPolicy;
import apec.discount.discount.GradeDiscountPolicy;
import apec.discount.discount.intf.DiscountPolicy;

/**
 * 할인 계산기 클래스
 * 사용자의 할인 정보를 바탕으로 여러 할인 정책을 적용하여 최종 금액을 계산합니다.
 * 
 * 책임:
 * - 할인 정책 생성 및 관리
 * - 할인 정책 적용 순서 관리
 * - 최종 할인 금액 계산
 * 
 * 이 클래스를 통해 User는 자신의 할인 정보만 제공하고,
 * 할인 계산 로직은 이 클래스가 담당합니다.
 */
public class DiscountCalculator {
    
    /**
     * 사용자의 할인 정보를 바탕으로 최종 금액을 계산합니다.
     * 
     * @param user 할인 정보를 가진 사용자
     * @param total 초기 총 금액
     * @return 모든 할인 적용 후 금액
     */
    public int calculateFinalTotal(User user, int total) {
        List<DiscountPolicy> policies = createDiscountPolicies(user);
        
        int finalTotal = total;
        for (DiscountPolicy policy : policies) {
            finalTotal = policy.applyDiscount(finalTotal);
        }
        
        return finalTotal;
    }
    
    /**
     * 사용자의 할인 정보를 바탕으로 할인 정책 목록을 생성합니다.
     * 등급 할인 -> 첫 구매 할인 -> 쿠폰 할인 순서로 적용됩니다.
     * 
     * 할인 적용 순서는 비즈니스 로직에 따라 변경될 수 있으며,
     * 이 메서드에서 중앙 집중식으로 관리됩니다.
     * 
     * @param user 할인 정보를 가진 사용자
     * @return 할인 정책 목록
     */
    private List<DiscountPolicy> createDiscountPolicies(User user) {
        List<DiscountPolicy> policies = new ArrayList<>();
        
        // 등급별 할인 (가장 먼저 적용)
        policies.add(new GradeDiscountPolicy(user.getMembershipLevel()));
        
        // 첫 구매 할인
        policies.add(new FirstPurchaseDiscountPolicy(user.isFirstPurchase()));
        
        // 쿠폰 할인 (가장 나중에 적용)
        if (user.hasCoupon()) {
            policies.add(new CouponDiscountPolicy(user.getCoupon()));
        }
        
        return policies;
    }
}

