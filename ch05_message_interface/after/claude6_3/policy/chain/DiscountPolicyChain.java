package ch05_message_interface.after.claude6_3.policy.chain;

import java.util.ArrayList;
import java.util.List;

import ch05_message_interface.after.claude6_3.policy.interfaces.DiscountPolicy;
/**
 * 할인 정책 체인
 * 여러 할인 정책을 순차적으로 적용합니다.
 */
public class DiscountPolicyChain {
    private final List<DiscountPolicy> policies = new ArrayList<>();

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

