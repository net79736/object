package apec.final_prjct.common;

import java.util.ArrayList;
import java.util.List;

import apec.final_prjct.repository.policy.intf.DiscountPolicy;

/**
 * 할인 정책 체인 클래스
 * 
 * Chain of Responsibility 패턴을 사용하여 여러 할인 정책을 순차적으로 적용합니다.
 * 할인 정책을 체인으로 관리하고, 순차적으로 적용하는 책임만 담당합니다.
 */
public class DiscountPolicyChain {
    private final List<DiscountPolicy> policies = new ArrayList<>();

    /**
     * 할인 정책을 체인에 추가합니다.
     * 
     * @param policy 추가할 할인 정책
     * @return 체인 인스턴스 (메서드 체이닝 지원)
     */
    public DiscountPolicyChain addPolicy(DiscountPolicy policy) {
        if (policy != null) {
            policies.add(policy);
        }
        return this;
    }

    /**
     * 모든 할인 정책을 순차적으로 적용합니다.
     * 
     * @param total 초기 총 금액
     * @return 모든 할인 적용 후 금액
     */
    public int applyAll(int total) {
        int result = total;
        for (DiscountPolicy policy : policies) {
            result = policy.applyDiscount(result);
        }
        return result;
    }
}
