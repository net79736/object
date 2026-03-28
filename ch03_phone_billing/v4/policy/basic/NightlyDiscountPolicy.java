package ch03_phone_billing.v4.policy.basic;

import java.time.Duration;

import common.Money;
import ch03_phone_billing.v4.domain.Call;import ch03_phone_billing.v4.policy.basic.intf.BasicRatePolicy;
public class NightlyDiscountPolicy extends BasicRatePolicy {
    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;
    
    public NightlyDiscountPolicy (Money regularAmount, Money nightlyAmount ,Duration seconds) {
        this.regularAmount = regularAmount;
        this.nightlyAmount = nightlyAmount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
            return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }
        
        return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}

