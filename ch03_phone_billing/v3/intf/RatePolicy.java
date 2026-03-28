package ch03_phone_billing.v3.intf;

import common.Money;
import ch03_phone_billing.v3.phone.Phone;
public interface RatePolicy {
    Money calculateFee(Phone phone);
}
