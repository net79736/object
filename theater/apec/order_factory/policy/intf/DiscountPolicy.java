package apec.order_factory.policy.intf;

import common.Money;

public interface DiscountPolicy {

    public Money applyDiscount(Money total); // 할인 적용 후 금액 반환
    
}
