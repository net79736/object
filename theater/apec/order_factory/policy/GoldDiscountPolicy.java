package apec.order_factory.policy;

import apec.order_factory.CustomerGradeEnum;
import apec.order_factory.policy.intf.DiscountPolicy;
import common.Money;

public class GoldDiscountPolicy implements DiscountPolicy {
    private CustomerGradeEnum grade;

    public GoldDiscountPolicy(CustomerGradeEnum grade) {
        this.grade = grade;
    }

    @Override
    public Money applyDiscount(Money total) {
        return total.times(1 - grade.getDiscountRate() / 100.0);
    }
}
