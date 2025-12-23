package apec.test5.discount.intf;

/**
 * 할인 정책을 나타내는 인터페이스
 * 
 * Strategy 패턴을 사용하여 다양한 할인 정책을 적용할 수 있습니다.
 * DIP 원칙에 따라 구체적인 할인 정책 구현체가 아닌 이 인터페이스에 의존합니다.
 */
public interface DiscountPolicy {
    /**
     * 총 금액에 할인을 적용합니다.
     * 
     * @param total 할인 전 총 금액
     * @return 할인 적용 후 금액
     */
    int applyDiscount(int total);
}

