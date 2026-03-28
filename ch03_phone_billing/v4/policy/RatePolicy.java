package ch03_phone_billing.v4.policy;

import common.Money;
import ch03_phone_billing.v4.domain.Phone;
public interface RatePolicy {
    Money calculateFee(Phone phone);
}

