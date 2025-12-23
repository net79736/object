package apec.test5.discount;

import apec.test5.common.MembershipLevel;
import apec.test5.discount.intf.DiscountPolicy;

/**
 * 회원 등급별 할인 정책
 * 
 * 회원의 등급에 따라 할인율을 적용합니다.
 * 일반 0%, 실버 5%, 골드 10%, VIP 15% 할인을 제공합니다.
 */
public class GradeDiscountPolicy implements DiscountPolicy {
    private final MembershipLevel membershipLevel;

    public GradeDiscountPolicy(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public int applyDiscount(int total) {
        int discountRate = membershipLevel.getDiscountRate();
        int discountAmount = (total * discountRate) / 100;
        return total - discountAmount;
    }
}

