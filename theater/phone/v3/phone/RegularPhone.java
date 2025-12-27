package phone.v3.phone;

import java.time.Duration;
import common.Money;
import phone.v3.common.Call;

public class RegularPhone extends Phone {
    private Money amount;
    private Duration seconds;

    public RegularPhone(Money amount, Duration seconds) {
        super(amount, seconds);
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times(calculateChargeUnits(call));
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

   // 세금 처리가 필요하지 않아 따로 afterCalculated 메서드를 @Override 하지 않음
}
