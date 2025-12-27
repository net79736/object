package phone.v3.intf;

import common.Money;
import phone.v3.phone.Phone;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
