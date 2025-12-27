package phone.v3.phone;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import common.Money;
import phone.v3.common.Call;

public abstract class Phone {
    private Money amount; // 요금
    private Duration seconds; // 통화 시간
    private List<Call> calls = new ArrayList<>();

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
            defaultAmount = defaultAmount.plus(calculateCallFee(call));
        }

        return afterCalculated(defaultAmount);
    }

    protected abstract Money calculateCallFee(Call call);

    /**
     * 요금 계산 후 처리 (훅 메서드)
     * @param fee
     * @return
     */
    protected Money afterCalculated(Money fee) {
        return fee;
    }
}