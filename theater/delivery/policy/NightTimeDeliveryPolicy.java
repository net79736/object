package delivery.policy;

import delivery.policy.intf.DeliveryFeePolicy;

/**
 * 심야 배송 정책
 * 
 * 기본 배송비에 심야 배송비 7,000원을 추가합니다.
 * 22시~6시 사이의 배송에 적용됩니다.
 * 
 * 합성 패턴의 장점:
 * - 독립적으로 구현되어 다른 정책과 자유롭게 조합 가능
 * - 심야 배송이 아닌 경우 제외 가능
 */
public class NightTimeDeliveryPolicy implements DeliveryFeePolicy {
    private static final int NIGHT_TIME_FEE = 7000;
    
    /**
     * 기본 배송비에 심야 배송비를 추가합니다.
     * 
     * @param baseFee 이전 정책이 계산한 배송비
     * @return 심야 배송비가 추가된 배송비
     */
    @Override
    public int calculateFee(int baseFee) {
        return baseFee + NIGHT_TIME_FEE;
    }
}

