package phone.v2;

import java.time.Duration;
import common.Money;

public class NightlyDiscountPhone extends Phone {
    private static final int LATE_NIGHT_HOUR = 22;
    
    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate) {
        super(taxRate);
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee (Call call) {
        if (isNightlyDiscount(call)) {
            return calculateAmount(call, nightlyAmount);  
        }
        
        return calculateAmount(call, regularAmount);
    }

    /**
     * 야간 할인 여부 검증
     * @param call
     * @return 야간 할인 여부
     */
    public boolean isNightlyDiscount(Call call) {
        return call.getFrom().getHour() >= LATE_NIGHT_HOUR;
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