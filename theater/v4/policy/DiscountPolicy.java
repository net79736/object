package v4.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import v4.Money;
import v4.Screening;
import v4.condition.DiscountCondition;

public abstract class DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList(); // 할인조건

    public DiscountPolicy (DiscountCondition  ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount (Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                // 할인 조건을 만족하면 할인 금액을 계산한다.
                return getDiscountAmount(screening);
            }
        }
        
        return Money.ZERO;
    }

    /**
     * 할인 금액을 계산한다.
     * @param screening 상영 정보
     * @return
     */
    abstract protected Money getDiscountAmount (Screening screening);
}
