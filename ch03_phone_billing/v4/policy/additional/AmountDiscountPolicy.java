package ch03_phone_billing.v4.policy.additional;

import common.Money;
import ch03_phone_billing.v4.policy.RatePolicy;import ch03_phone_billing.v4.policy.additional.intf.AdditionalRatePolicy;
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

