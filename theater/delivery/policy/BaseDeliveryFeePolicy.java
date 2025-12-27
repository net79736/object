package delivery.policy;

import delivery.policy.intf.DeliveryFeePolicy;

/**
 * 기본 배송비 계산 정책
 * 
 * 거리를 기반으로 기본 배송비를 계산합니다.
 * 다른 정책들의 기반이 되는 기본 정책입니다.
 * 
 * 합성 패턴의 장점:
 * - 기본 정책을 독립적으로 구현하여 재사용 가능
 * - 다른 정책들과 조합하여 사용 가능
 */
public class BaseDeliveryFeePolicy implements DeliveryFeePolicy {
    private final int distance;
    
    /**
     * 기본 배송비 정책 생성자
     * 
     * @param distance 배송 거리 (km)
     */
    public BaseDeliveryFeePolicy(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("거리는 0 이상이어야 합니다.");
        }
        this.distance = distance;
    }
    
    /**
     * 거리를 기반으로 기본 배송비를 계산합니다.
     * 
     * @param baseFee 이전 정책이 계산한 배송비 (기본 정책이므로 무시됨)
     * @return 거리 * 1000원
     */
    @Override
    public int calculateFee(int baseFee) {
        return distance * 1000;
    }
    
    /**
     * 배송 거리를 반환합니다.
     * 
     * @return 배송 거리 (km)
     */
    public int getDistance() {
        return distance;
    }
}

