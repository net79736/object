package test.claude6_3.policy.interfaces;

/**
 * 할인 정책 인터페이스
 * 전략 패턴을 사용하여 다양한 할인 정책을 적용할 수 있습니다.
 */
public interface DiscountPolicy {
    /**
     * 할인을 적용합니다.
     * 
     * @param total 할인 전 총 금액
     * @return 할인 후 금액
     */
    int applyDiscount(int total);
}

