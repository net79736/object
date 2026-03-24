package v99;

import java.util.Arrays;

import common.Money;
import v0.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition ... conditions) {
        super(Arrays.asList(conditions));
        this.percent = percent;
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return screening.getMovie().getFee().times(percent);
    }
}
