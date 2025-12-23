package apec.final_prjct.repository.policy;

import java.util.Arrays;
import java.util.List;

import apec.final_prjct.repository.condition.intf.DiscountCondition;
import apec.final_prjct.repository.policy.intf.DiscountPolicy;

/**
 * 퍼센트 할인 정책
 * 
 * 총 금액에 퍼센트 할인을 적용합니다.
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class PercentDiscountPolicy implements DiscountPolicy {

    private final double discountRate;
    private final List<DiscountCondition> conditions;
    private final DiscountType discountType = DiscountType.PERCENT_DISCOUNT;

    public PercentDiscountPolicy(double discountRate, DiscountCondition... conditions) {
        this.discountRate = discountRate;
        this.conditions = Arrays.asList(conditions);
    }

    public double getDiscountRate() {
        return discountRate;
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
        return (int)(total * (1 - discountRate));
    }
}
