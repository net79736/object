package v4.policy;

import v4.Money;
import v4.Screening;
import v4.condition.DiscountCondition;

/**
 * 고정 금액 할인 정책을 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-04
 */
public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy (Money discountAmount, DiscountCondition ... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount; // 고정 금액 할인 정책을 적용한다.
    }
}
