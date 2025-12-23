package apec.final_prjct.service.condition.intf;

import apec.final_prjct.domain.Order;

public interface DiscountCondition {
    boolean isSatisfied(Order order);
}
