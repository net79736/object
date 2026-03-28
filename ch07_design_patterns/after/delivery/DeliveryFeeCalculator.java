package ch07_design_patterns.after.delivery;

import ch07_design_patterns.after.delivery.common.DeliveryFeePolicyChain;import ch07_design_patterns.after.delivery.policy.BaseDeliveryFeePolicy;import ch07_design_patterns.after.delivery.policy.intf.DeliveryFeePolicy;
/**
 * 배송비 계산기 클래스
 * 
 * 배송비 계산을 담당하는 서비스 클래스입니다.
 * 기본 배송비 정책과 추가 정책들을 조합하여 최종 배송비를 계산합니다.
 * 
 * 합성 패턴의 장점:
 * - 다양한 정책 조합을 동적으로 생성 가능
 * - 정책 적용 순서를 자유롭게 변경 가능
 * - 새로운 정책 추가 시 이 클래스 수정 불필요
 */
public class DeliveryFeeCalculator {
    
    /**
     * 배송비를 계산합니다.
     * 
     * @param distance 배송 거리 (km)
     * @param policies 적용할 배송비 정책들 (순서대로 적용됨)
     * @return 최종 배송비
     */
    public int calculate(int distance, DeliveryFeePolicy... policies) {
        // 기본 배송비 계산
        BaseDeliveryFeePolicy basePolicy = new BaseDeliveryFeePolicy(distance);
        int baseFee = basePolicy.calculateFee(0);
        
        // 추가 정책들을 체인에 추가하여 순차적으로 적용
        DeliveryFeePolicyChain chain = new DeliveryFeePolicyChain();
        for (DeliveryFeePolicy policy : policies) {
            chain.addPolicy(policy);
        }
        
        // 모든 정책 적용
        return chain.applyAll(baseFee);
    }
    
    /**
     * 배송비를 계산합니다. (체인을 직접 전달받는 방식)
     * 
     * @param distance 배송 거리 (km)
     * @param chain 적용할 배송비 정책 체인
     * @return 최종 배송비
     */
    public int calculate(int distance, DeliveryFeePolicyChain chain) {
        // 기본 배송비 계산
        BaseDeliveryFeePolicy basePolicy = new BaseDeliveryFeePolicy(distance);
        int baseFee = basePolicy.calculateFee(0);
        
        // 체인에 있는 모든 정책 적용
        return chain.applyAll(baseFee);
    }
}

