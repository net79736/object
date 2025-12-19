package test.claude6_3.policy;

import test.claude6_3.enums.MembershipLevel;
import test.claude6_3.policy.interfaces.DiscountPolicy;

/**
 * 회원 등급별 할인 정책
 */
public class MembershipDiscountPolicy implements DiscountPolicy {
    private final MembershipLevel membershipLevel;

    public MembershipDiscountPolicy(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public int applyDiscount(int total) {
        if (membershipLevel == MembershipLevel.GOLD) {
            return (int)(total * 0.9); // 10% 할인
        } else if (membershipLevel == MembershipLevel.SILVER) {
            return (int)(total * 0.95); // 5% 할인
        }
        return total;
    }
}

