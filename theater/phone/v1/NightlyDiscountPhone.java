package phone.v1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import common.Money;

public class NightlyDiscountPhone {
    private static final int LATE_NIGHT_HOUR = 22; // 야간 할인 시작 시간

    private Money nightlyAmount; // 야간 요금
    private Money regularAmount; // 낮 요금
    private Duration seconds; // 통화 시간
    private List<Call> calls = new ArrayList<>(); // 통화 기록

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            if (isNightlyDiscount(call)) {
                result = result.plus(calculateNightlyAmount(call));
            } else {
                result = result.plus(calculateRegularAmount(call));
            }
        }

        return result;
    }

    /**
     * 야간 할인 여부 검증
     * @param call
     * @return 야간 할인 여부
     */
    public boolean isNightlyDiscount(Call call) {
        return call.getFrom().getHour() >= LATE_NIGHT_HOUR;
    }

    /**
     * 야간 요금 계산
     * @param call
     * @return
     */
    public Money calculateNightlyAmount(Call call) {
        return calculateAmount(call, nightlyAmount);
    }

    /**
     * 낮 요금 계산
     * @param call
     * @return
     */
    public Money calculateRegularAmount(Call call) {
        return calculateAmount(call, regularAmount);
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
