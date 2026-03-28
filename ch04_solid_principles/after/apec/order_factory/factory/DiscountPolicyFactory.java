package ch04_solid_principles.after.apec.order_factory.factory;

import ch04_solid_principles.after.apec.order_factory.CustomerGradeEnum;import ch04_solid_principles.after.apec.order_factory.policy.GoldDiscountPolicy;import ch04_solid_principles.after.apec.order_factory.policy.NormalDiscountPolicy;import ch04_solid_principles.after.apec.order_factory.policy.VIPDiscountPolicy;import ch04_solid_principles.after.apec.order_factory.policy.intf.DiscountPolicy;
public class DiscountPolicyFactory {

    public DiscountPolicy create(CustomerGradeEnum grade) {
        return switch (grade) {
            case VIP -> new VIPDiscountPolicy(grade);
            case GOLD -> new GoldDiscountPolicy(grade);
            case NORMAL -> new NormalDiscountPolicy(grade);
            default -> new NormalDiscountPolicy(grade);
        };
    }

}
