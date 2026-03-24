package v99;

import java.util.List;

import common.Money;
import v0.Screening;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions;

    public DiscountPolicy(List<DiscountCondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * 할인 금액 계산
     * 할인 조건을 만족하는 경우 할인 금액 계산
     * 
     * @param screening 상영 정보 (상영 시간 정보)
     * @return 할인 금액 (예: 1000원)
     */
    public Money calculateDiscountAmount(Screening screening) {
        // 할인 조건을 루프 돌며 할인 조건을 검증
        for (DiscountCondition condition : conditions) {
            if (condition.isDiscountable(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    public abstract Money getDiscountAmount(Screening screening);
}
