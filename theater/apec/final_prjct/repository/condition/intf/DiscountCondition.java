package apec.final_prjct.repository.condition.intf;

import apec.final_prjct.domain.Order;

public interface DiscountCondition {
    boolean isSatisfied(Order order);
}
