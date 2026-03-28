package ch07_design_patterns.after.apec.final_prjct.repository.policy;

import java.util.Arrays;
import java.util.List;

import ch07_design_patterns.after.apec.final_prjct.repository.condition.intf.DiscountCondition;import ch07_design_patterns.after.apec.final_prjct.repository.policy.intf.DiscountPolicy;
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
    private final DiscountType discountType = DiscountType.FIXED_AMOUNT;

    public FixedAmountDiscountPolicy(int discountAmount, DiscountCondition... conditions) {
        this.discountAmount = discountAmount;
        this.conditions = Arrays.asList(conditions);
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public List<DiscountCondition> getConditions() {
        return conditions;
    }

    @Override
    public DiscountType getDiscountType() {
        return discountType;
    }

    @Override
    public int applyDiscount(int total) {
        return total - discountAmount;
    }
}
