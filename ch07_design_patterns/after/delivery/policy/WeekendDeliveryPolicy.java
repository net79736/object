package ch07_design_patterns.after.delivery.policy;

import ch07_design_patterns.after.delivery.policy.intf.DeliveryFeePolicy;
/**
 * 주말 배송 정책
 * 
 * 기본 배송비에 주말 할증 50%를 적용합니다.
 * 
 * 합성 패턴의 장점:
 * - 독립적으로 구현되어 다른 정책과 자유롭게 조합 가능
 * - 주말이 아닌 경우 제외 가능
 */
public class WeekendDeliveryPolicy implements DeliveryFeePolicy {
    private static final double WEEKEND_SURCHARGE_RATE = 1.5;
    
    /**
     * 기본 배송비에 주말 할증을 적용합니다.
     * 
     * @param baseFee 이전 정책이 계산한 배송비
     * @return 주말 할증이 적용된 배송비 (50% 증가)
     */
    @Override
    public int calculateFee(int baseFee) {
        return (int) (baseFee * WEEKEND_SURCHARGE_RATE);
    }
}

