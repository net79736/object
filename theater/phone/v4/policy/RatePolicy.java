package phone.v4.policy;

import common.Money;
import phone.v4.domain.Phone;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}

