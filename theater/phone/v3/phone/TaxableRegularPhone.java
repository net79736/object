package phone.v3.phone;

import java.time.Duration;
import common.Money;
import phone.v3.common.Call;

public class TaxableRegularPhone extends Phone {
    private double taxRate;

    /**
     * 세금 부과 통화 전화 생성
     * @param amount 단위 시간당 요금
     * @param seconds 요금 부과 시간 단위
     * @param taxRate 세금 비율
     */
    public TaxableRegularPhone(Money amount, Duration seconds, double taxRate) {
        super(amount, seconds);
        this.taxRate = taxRate;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return getAmount().times(calculateChargeUnits(call));
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.plus(fee.times(taxRate));
    }
}