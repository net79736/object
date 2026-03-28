package ch03_phone_billing.v3.policy;

import java.time.Duration;
import common.Money;
import ch03_phone_billing.v3.common.Call;
public class RegularPolicy extends BasicRatePolicy {
    private Money amount; // 단위 시간당 요금
    private Duration seconds; // 요금 부과 시간 단위

    public RegularPolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    /**
     * 개별 통화 요금 계산
     * 부모 클래스의 공통 메서드를 활용하여 중복을 제거합니다.
     */
    @Override
    protected Money caculateCallFee(Call call) {
        int chargeUnits = calculateChargeUnits(call, seconds); // 통화 시간을 요금 부과 시간 단위로 나누어 몫을 반환
        return calculateAmount(amount, chargeUnits); // 요금 부과 단위 수와 단위 시간당 요금을 곱하여 요금을 계산합니다.
    }
}
