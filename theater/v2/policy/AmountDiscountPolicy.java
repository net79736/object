package v2.policy;

import java.util.Arrays;

import common.Money;
import v2.Screening;
import v2.condition.DiscountCondition;

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
