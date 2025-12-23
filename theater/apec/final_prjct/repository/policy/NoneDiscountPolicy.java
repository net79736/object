package apec.final_prjct.repository.policy;

import apec.final_prjct.repository.policy.intf.DiscountPolicy;

/**
 * 미적용 할인 정책
 * 
 * 총 금액에 미적용 할인을 적용합니다.
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class NoneDiscountPolicy implements DiscountPolicy {

    private final DiscountType discountType = DiscountType.NONE_DISCOUNT;

    public NoneDiscountPolicy() {}

    @Override
    public DiscountType getDiscountType() {
        return discountType;
    }

    @Override
    public int applyDiscount(int total) {
        return total;
    }
}
