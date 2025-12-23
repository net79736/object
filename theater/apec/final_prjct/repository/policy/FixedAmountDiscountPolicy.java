package apec.final_prjct.repository.policy;

import java.util.Arrays;
import java.util.List;

import apec.final_prjct.repository.condition.intf.DiscountCondition;
import apec.final_prjct.repository.policy.intf.DiscountPolicy;

/**
 * 퍼센트 할인 정책
 * 
 * 총 금액에 퍼센트 할인을 적용합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class FixedAmountDiscountPolicy implements DiscountPolicy {

    private final int discountAmount;
    private final List<DiscountCondition> conditions;

    public FixedAmountDiscountPolicy(int discountAmount, DiscountCondition... conditions) {
        this.discountAmount = discountAmount;
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public int applyDiscount(int total) {
        return total - discountAmount;
    }
}
