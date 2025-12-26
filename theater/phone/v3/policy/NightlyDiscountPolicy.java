package phone.v3.policy;

import java.time.Duration;

import common.Money;
import phone.v3.common.Call;

public class NightlyDiscountPolicy extends BasicRatePolicy {
    private static final int LATE_NIGHT_HOUR = 22;
  
    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;
    
    public NightlyDiscountPolicy(Money regularAmount, Money nightlyAmount, Duration seconds) {
      this.regularAmount = regularAmount;
      this.nightlyAmount = nightlyAmount;
      this.seconds = seconds;
    }
    
    /**
     * 개별 통화 요금 계산
     * 야간 할인 여부에 따라 적절한 요금을 선택하고,
     * 부모 클래스의 공통 메서드를 활용하여 계산합니다.
     */
    @Override
    protected Money caculateCallFee(Call call) {
      Money rate = selectRate(call);
      int chargeUnits = calculateChargeUnits(call, seconds);
      return calculateAmount(rate, chargeUnits);
    }

    /**
     * 통화 시간대에 따라 적용할 요금을 선택합니다.
     * 이 메서드가 NightlyDiscountPolicy의 핵심 책임입니다.
     * 
     * @param call 통화 정보
     * @return 적용할 요금 (야간 할인 또는 일반 요금)
     */
    private Money selectRate(Call call) {
        if (isNightlyDiscount(call)) {
            return nightlyAmount;
        }
        return regularAmount;
    }

    /**
     * 야간 할인 여부 검증
     * 통화 시작 시간이 22시 이후인지 확인합니다.
     * 
     * @param call 통화 정보
     * @return 야간 할인 적용 여부
     */
    private boolean isNightlyDiscount(Call call) {
        return call.getFrom().getHour() >= LATE_NIGHT_HOUR;
    }
}
