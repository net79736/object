package v2.policy;

import java.util.Arrays;

import common.Money;
import v2.Screening;
import v2.condition.DiscountCondition;

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
