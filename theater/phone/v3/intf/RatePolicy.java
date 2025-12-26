package phone.v3.intf;

import common.Money;
import phone.v3.common.Phone;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
