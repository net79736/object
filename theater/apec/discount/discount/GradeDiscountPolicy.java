package apec.discount.discount;

import apec.discount.discount.intf.DiscountPolicy;
import apec.discount.enums.MembershipLevelEnum;

public class GradeDiscountPolicy implements DiscountPolicy {
    private MembershipLevelEnum membershipLevel;

    public GradeDiscountPolicy(MembershipLevelEnum membershipLevel) {
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
