package phone.v4.policy.basic.intf;

import common.Money;
import phone.v4.domain.Call;
import phone.v4.domain.Phone;
import phone.v4.policy.RatePolicy;

public abstract class BasicRatePolicy implements RatePolicy {
    @Override
    public Money calculateFee(Phone phone) {
        Money result = Money.ZERO;

        for (Call call : phone.getCalls()) {
            result = result.plus(calculateCallFee(call));
        }

        return result;
    }

    protected abstract Money calculateCallFee(Call call);
}

