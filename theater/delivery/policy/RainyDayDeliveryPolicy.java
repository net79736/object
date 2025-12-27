package delivery.policy;

import delivery.policy.intf.DeliveryFeePolicy;

/**
 * 우천 배송 정책
 * 
 * 기본 배송비에 우천 추가 요금 3,000원을 추가합니다.
 * 
 * 합성 패턴의 장점:
 * - 독립적으로 구현되어 다른 정책과 자유롭게 조합 가능
 * - 날씨가 좋은 경우 제외 가능
 */
public class RainyDayDeliveryPolicy implements DeliveryFeePolicy {
    private static final int RAINY_DAY_FEE = 3000;
    
    /**
     * 기본 배송비에 우천 추가 요금을 추가합니다.
     * 
     * @param baseFee 이전 정책이 계산한 배송비
     * @return 우천 추가 요금이 추가된 배송비
     */
    @Override
    public int calculateFee(int baseFee) {
        return baseFee + RAINY_DAY_FEE;
    }
}

