package ch02_discount_policy.after.theater.v2.policy;

import java.util.Arrays;

import common.Money;
import ch02_discount_policy.after.theater.v2.Screening;import ch02_discount_policy.after.theater.v2.condition.DiscountCondition;
public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition ... conditions) {
        super(Arrays.asList(conditions));
        this.percent = percent;
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
