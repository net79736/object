package ch03_phone_billing.v3.policy;

import java.time.Duration;
import common.Money;
import ch03_phone_billing.v3.common.Call;import ch03_phone_billing.v3.intf.RatePolicy;import ch03_phone_billing.v3.phone.Phone;
public abstract class BasicRatePolicy implements RatePolicy  {
    
    @Override
    public Money calculateFee(Phone phone) {
        Money result = Money.ZERO;
    
        for (Call call : phone.getCalls()) {
            // 개별 통화 요금 계산
            result = result.plus(caculateCallFee(call));
        }
        
        return result;
    }

    /**
     * 개별 통화 요금 계산
     * @param call
     * @return
     */
    protected abstract Money caculateCallFee(Call call);
    
    /**
     * 통화 시간을 요금 부과 시간 단위로 나누어 몫을 반환합니다.
     * 공통 로직을 부모 클래스로 이동하여 중복을 제거합니다.
     * 
     * @param call 통화 정보
     * @param seconds 요금 부과 시간 단위
     * @return 요금 부과 단위 수
     */
    protected int calculateChargeUnits(Call call, Duration seconds) {
        // 통화 시간 / 요금 부과 시간
        return (int) (call.getDuration().getSeconds() / seconds.getSeconds());
    }
    
    /**
     * 요금 계산 공통 로직
     * rate와 chargeUnits를 곱하여 요금을 계산합니다.
     * 
     * @param rate 단위 시간당 요금
     * @param chargeUnits 요금 부과 단위 수
     * @return 계산된 요금
     */
    protected Money calculateAmount(Money rate, int chargeUnits) {
        return rate.times(chargeUnits);
    }
}
