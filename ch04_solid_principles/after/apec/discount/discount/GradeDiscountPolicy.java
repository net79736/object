package ch04_solid_principles.after.apec.discount.discount;

import ch04_solid_principles.after.apec.discount.discount.intf.DiscountPolicy;import ch04_solid_principles.after.apec.discount.enums.MembershipLevelEnum;
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
