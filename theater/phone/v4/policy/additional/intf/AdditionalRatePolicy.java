package phone.v4.policy.additional.intf;

import common.Money;
import phone.v4.domain.Phone;
import phone.v4.policy.RatePolicy;

public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }
    
    protected abstract Money afterCalculated(Money fee);
}

