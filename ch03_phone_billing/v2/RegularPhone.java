package ch03_phone_billing.v2;

import java.time.Duration;

import common.Money;

public class RegularPhone extends Phone {
    private Money amount;
    private Duration seconds;
    
    public RegularPhone (Money amount, Duration seconds, double taxRate) {
        super(taxRate);
        this.amount = amount;
        this.seconds = seconds;
    }
    @Override
    protected Money calculateCallFee (Call call) {
        return calculateAmount(call, amount);
    }

    private Money calculateAmount(Call call, Money rate) {
        return rate.times(calculateChargeUnits(call));
    }

    /**
     * 통화 시간을 요금 부과 시간 단위로 나누어 몫을 반환합니다.
     * 
     * @param call
     * @return
     */
    private int calculateChargeUnits(Call call) {
        // 통화 시간 / 요금 부과 시간
        return (int) (call.getDuration().getSeconds() / seconds.getSeconds()); // 통화 시간 계산
    }
}
