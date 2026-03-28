package ch03_phone_billing.v1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import common.Money;

public class Phone {
    private Money amount; // 요금
    private Duration seconds; // 통화 시간
    private List<Call> calls = new ArrayList<>();
    private double taxRate;

    public Phone(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public Money getAmount() {
        return amount;
    }

    public Duration getSeconds() {
        return seconds;
    }

    public Money calculateFee() {
        Money defaultAmount = Money.ZERO;

        for (Call call : calls) {
            defaultAmount = defaultAmount.plus(amount.times(calculateChargeUnits(call)));
        }

        return defaultAmount;
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
