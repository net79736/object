package ch02_discount_policy.after.theater.v2.policy;

import java.util.List;

import common.Money;
import ch02_discount_policy.after.theater.v2.Screening;import ch02_discount_policy.after.theater.v2.condition.DiscountCondition;
public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions; // 할인 조건들

    public DiscountPolicy(List<DiscountCondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * 할인 금액 계산
     * @param screening 상영 정보
     * @return 할인 금액
     */
    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    /**
     * 할인 금액 계산
     *  1. 금액 할인 정책: 고정 금액 할인
     *  2. 비율 할인 정책: 비율 할인
     * @param screening 상영 정보
     * @return 할인 금액
     */
    protected abstract Money getDiscountAmount(Screening screening);
}
