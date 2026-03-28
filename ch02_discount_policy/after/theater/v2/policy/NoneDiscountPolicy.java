package ch02_discount_policy.after.theater.v2.policy;

import java.util.Collections;

import common.Money;
import ch02_discount_policy.after.theater.v2.Screening;
public class NoneDiscountPolicy extends DiscountPolicy {
    public NoneDiscountPolicy() {
        super(Collections.emptyList());
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
