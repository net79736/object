package v99;

import java.util.Arrays;

import common.Money;
import v0.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount; // 할인 금액

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions) {
        super(Arrays.asList(conditions));
        this.discountAmount = discountAmount;
    }

    /**
     * 할인 금액 계산
     * 할인 조건을 만족하는 경우 할인 금액 계산
     * 
     * @param screening 상영 정보 (상영 시간 정보)
     * @return 할인 금액 (예: 1000원)
     */
    @Override
    public Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
