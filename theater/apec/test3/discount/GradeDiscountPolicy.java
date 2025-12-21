package apec.test3.discount;

import apec.test3.MembershipLevel;
import apec.test3.discount.intf.DiscountPolicy;

public class GradeDiscountPolicy implements DiscountPolicy {
    private MembershipLevel membershipLevel;

    public GradeDiscountPolicy(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public int applyDiscount(int total) {
        return switch (membershipLevel) {
            case GOLD -> (int)(total * 0.9);
            case SILVER -> (int)(total * 0.95);
            case BRONZE -> total;
        };
    }
}
