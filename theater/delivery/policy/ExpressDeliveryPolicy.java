package delivery.policy;

import delivery.policy.intf.DeliveryFeePolicy;

/**
 * 특급 배송 정책
 * 
 * 기본 배송비에 특급 배송비 5,000원을 추가합니다.
 * 
 * 합성 패턴의 장점:
 * - 독립적으로 구현되어 다른 정책과 자유롭게 조합 가능
 * - 특급 배송이 필요 없는 경우 제외 가능
 */
public class ExpressDeliveryPolicy implements DeliveryFeePolicy {
    private static final int EXPRESS_FEE = 5000;
    
    /**
     * 기본 배송비에 특급 배송비를 추가합니다.
     * 
     * @param baseFee 이전 정책이 계산한 배송비
     * @return 특급 배송비가 추가된 배송비
     */
    @Override
    public int calculateFee(int baseFee) {
        return baseFee + EXPRESS_FEE;
    }
}

