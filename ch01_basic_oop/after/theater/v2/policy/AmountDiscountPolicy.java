package ch01_basic_oop.after.theater.v2.policy;

import java.util.Arrays;

import common.Money;
import ch01_basic_oop.after.theater.v2.Screening;import ch01_basic_oop.after.theater.v2.condition.DiscountCondition;
public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions) {
        super(Arrays.asList(conditions));
        this.discountAmount = discountAmount;
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
