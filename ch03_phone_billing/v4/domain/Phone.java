package ch03_phone_billing.v4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.Money;
import ch03_phone_billing.v4.policy.RatePolicy;
public class Phone {
    private RatePolicy ratePolicy;
    private List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }

    public List<Call> getCalls() {
        return Collections.unmodifiableList(calls);
    }

    public Money calculateFee() {
        return ratePolicy.calculateFee(this);
    }
}

