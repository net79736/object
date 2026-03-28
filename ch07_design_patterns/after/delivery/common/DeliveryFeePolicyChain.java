package ch07_design_patterns.after.delivery.common;

import java.util.ArrayList;
import java.util.List;

import ch07_design_patterns.after.delivery.policy.intf.DeliveryFeePolicy;
/**
 * 배송비 정책 체인 클래스
 * 
 * Chain of Responsibility 패턴을 사용하여 여러 배송비 정책을 순차적으로 적용합니다.
 * 배송비 정책을 체인으로 관리하고, 순차적으로 적용하는 책임만 담당합니다.
 * 
 * 합성 패턴의 장점:
 * - 정책들을 리스트로 관리하여 런타임에 자유롭게 조합 가능
 * - 정책 적용 순서를 동적으로 변경 가능
 * - 새로운 정책 추가 시 이 클래스 수정 불필요
 */
public class DeliveryFeePolicyChain {
    private final List<DeliveryFeePolicy> policies = new ArrayList<>();
    
    /**
     * 배송비 정책을 체인에 추가합니다.
     * 
     * @param policy 추가할 배송비 정책
     * @return 체인 인스턴스 (메서드 체이닝 지원)
     */
    public DeliveryFeePolicyChain addPolicy(DeliveryFeePolicy policy) {
        if (policy != null) {
            policies.add(policy);
        }
        return this;
    }
    
    /**
     * 모든 배송비 정책을 순차적으로 적용합니다.
     * 
     * @param initialFee 초기 배송비 (기본 정책에서 계산된 값)
     * @return 모든 정책이 적용된 최종 배송비
     */
    public int applyAll(int initialFee) {
        int result = initialFee;
        for (DeliveryFeePolicy policy : policies) {
            result = policy.calculateFee(result);
        }
        return result;
    }
    
    /**
     * 체인에 추가된 정책의 개수를 반환합니다.
     * 
     * @return 정책 개수
     */
    public int getPolicyCount() {
        return policies.size();
    }
}

