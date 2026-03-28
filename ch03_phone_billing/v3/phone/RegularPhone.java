package ch03_phone_billing.v3.phone;

import java.time.Duration;
import common.Money;
import ch03_phone_billing.v3.common.Call;
public class RegularPhone extends Phone {
    /**
     * 일반 통화 전화 생성
     * @param amount 단위 시간당 요금
     * @param seconds 요금 부과 시간 단위
     */
    public RegularPhone(Money amount, Duration seconds) {
        super(amount, seconds);
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return getAmount().times(calculateChargeUnits(call));
    }

   // 세금 처리가 필요하지 않아 따로 afterCalculated 메서드를 @Override 하지 않음
}
