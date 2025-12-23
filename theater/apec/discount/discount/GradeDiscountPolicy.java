package apec.discount.discount;

import apec.discount.MembershipLevel;
import apec.discount.discount.intf.DiscountPolicy;

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
