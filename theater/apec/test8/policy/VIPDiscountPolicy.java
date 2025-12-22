package apec.test8.policy;

import apec.test8.CustomerGradeEnum;
import apec.test8.policy.intf.DiscountPolicy;
import common.Money;

public class VIPDiscountPolicy implements DiscountPolicy {
    private CustomerGradeEnum grade;

    public VIPDiscountPolicy(CustomerGradeEnum grade) {
        this.grade = grade;
    }

    @Override
    public Money applyDiscount(Money total) {
        return total.times(1 - grade.getDiscountRate() / 100.0);
    }
}
