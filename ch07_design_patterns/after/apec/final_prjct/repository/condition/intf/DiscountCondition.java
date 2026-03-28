package ch07_design_patterns.after.apec.final_prjct.repository.condition.intf;

import ch07_design_patterns.after.apec.final_prjct.domain.Order;
public interface DiscountCondition {
    boolean isSatisfied(Order order);
}
