package phone.v4.policy.additional;

import common.Money;
import phone.v4.policy.RatePolicy;
import phone.v4.policy.additional.intf.AdditionalRatePolicy;

public class TaxablePolicy extends AdditionalRatePolicy  {
    private double taxRatio;
  
    public TaxablePolicy(double taxRatio, RatePolicy basePolicy) {
        super(basePolicy);
        this.taxRatio = taxRatio;
    }

    /**
     * 세액 추가 (훅 메서드)
     * @param fee 요금 계산 결과
     * @return 세액 추가 요금
     */
    @Override
    protected Money afterCalculated(Money fee) {
        return fee.plus(fee.times(taxRatio));
    }
}

