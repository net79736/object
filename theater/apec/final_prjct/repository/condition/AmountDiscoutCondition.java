package apec.final_prjct.repository.condition;

import apec.final_prjct.domain.Order;
import apec.final_prjct.repository.condition.intf.DiscountCondition;

/**
 * 금액 할인 조건
 * 주문 금액이 특정 금액 이상일 때 할인을 적용합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class AmountDiscoutCondition implements DiscountCondition {

    private final int amount;

    public AmountDiscoutCondition(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return false;
    }
}
