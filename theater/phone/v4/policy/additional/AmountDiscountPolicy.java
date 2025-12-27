package phone.v4.policy.additional;

import common.Money;
import phone.v4.policy.RatePolicy;
import phone.v4.policy.additional.intf.AdditionalRatePolicy;

public class AmountDiscountPolicy extends AdditionalRatePolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, RatePolicy basePolicy) {
        super(basePolicy);
        this.discountAmount = discountAmount;
    }
    
    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }
}

