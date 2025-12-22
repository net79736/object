package apec.test8.factory;

import apec.test8.CustomerGradeEnum;
import apec.test8.policy.GoldDiscountPolicy;
import apec.test8.policy.NormalDiscountPolicy;
import apec.test8.policy.VIPDiscountPolicy;
import apec.test8.policy.intf.DiscountPolicy;

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
