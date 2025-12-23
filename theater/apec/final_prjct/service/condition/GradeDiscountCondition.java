package apec.final_prjct.service.condition;

import apec.final_prjct.domain.Order;
import apec.final_prjct.service.condition.intf.DiscountCondition;

/**
 * 회원 등급 할인 조건
 * 회원 등급에 따라 할인을 적용합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class GradeDiscountCondition implements DiscountCondition {

    @Override
    public boolean isSatisfied(Order order) {
        return false;
    }
    
}
