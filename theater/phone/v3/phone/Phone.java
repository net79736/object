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

    /**
     * 전체 통화 목록의 요금 계산
     * @return 전체 통화 목록의 요금
     */
    public Money calculateFee() {
        Money defaultAmount = Money.ZERO;

        for (Call call : calls) {
            defaultAmount = defaultAmount.plus(calculateCallFee(call));
        }

        return afterCalculated(defaultAmount);
    }

    /**
     * 개별 통화 요금 계산
     * @param call
     * @return 개별 통화 요금
     */
    protected abstract Money calculateCallFee(Call call);

    /**
     * 요금 계산 후 처리 (훅 메서드)
     * @param fee
     * @return
     */
    protected Money afterCalculated(Money fee) {
        return fee;
    }

    /**
     * 통화 시간을 요금 부과 시간 단위로 나누어 몫을 반환합니다.
     * 
     * @param call
     * @return
     */
    protected int calculateChargeUnits(Call call) {
        // 통화 시간 / 요금 부과 시간
        return (int) (call.getDuration().getSeconds() / getSeconds().getSeconds()); // 통화 시간 계산
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
}