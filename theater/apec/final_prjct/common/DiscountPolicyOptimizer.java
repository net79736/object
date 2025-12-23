package apec.final_prjct.common;

import java.util.ArrayList;
import java.util.List;

import apec.final_prjct.repository.policy.FixedAmountDiscountPolicy;
import apec.final_prjct.repository.policy.PercentDiscountPolicy;
import apec.final_prjct.repository.policy.intf.DiscountPolicy;

/**
 * 할인 정책 최적화 유틸리티 클래스
 * 
 * 동일한 타입의 할인 정책이 여러 개 있을 경우, 
 * 더 높은 할인을 주는 정책만 선택하여 최적화합니다.
 * 
 * Single Responsibility Principle: 할인 정책 최적화만 담당합니다.
 */
public class DiscountPolicyOptimizer {

    private final static Integer MAX_DISCOUNT_AMOUNT = 100000; // 최대 할인 금액
    
    /**
     * 여러 할인 정책 중 동일한 타입의 정책이 있으면 더 높은 할인을 주는 정책만 선택합니다.
     * 
     * @param policies 최적화할 할인 정책 리스트
     * @param baseAmount 할인 금액을 비교하기 위한 기준 금액 (퍼센트 할인의 경우 필요)
     * @return 동일 타입의 정책 중 더 높은 할인을 주는 정책만 포함된 리스트
     */
    public static List<DiscountPolicy> optimize(List<DiscountPolicy> policies, int baseAmount) {
        if (policies == null || policies.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<DiscountPolicy> optimizedPolicies = new ArrayList<>();
        
        // 각 정책을 순회하면서 동일 타입이 있으면 더 높은 할인을 선택
        for (DiscountPolicy policy : policies) {
            if (policy == null) {
                continue;
            }
            
            // 이미 추가된 정책 중 동일한 타입이 있는지 확인
            DiscountPolicy existingSameType = null;
            for (DiscountPolicy existing : optimizedPolicies) {
                if (existing.getDiscountType() == policy.getDiscountType()) {
                    existingSameType = existing;
                    break;
                }
            }
            
            if (existingSameType != null) {
                // 동일 타입이 있으면 할인 금액 비교
                int existingDiscount = calculateDiscountAmount(existingSameType, baseAmount);
                int newDiscount = calculateDiscountAmount(policy, baseAmount);
                
                // 더 높은 할인을 주는 정책으로 교체
                if (newDiscount > existingDiscount) {
                    optimizedPolicies.remove(existingSameType);
                    optimizedPolicies.add(policy);
                }
            } else {
                // 동일 타입이 없으면 그대로 추가
                optimizedPolicies.add(policy);
            }
        }
        
        return optimizedPolicies;
    }
    
    /**
     * 할인 정책의 실제 할인 금액을 계산합니다.
     * 
     * @param policy 할인 정책
     * @param baseAmount 기준 금액
     * @return 실제 할인 금액
     */
    private static int calculateDiscountAmount(DiscountPolicy policy, int baseAmount) {
        switch (policy.getDiscountType()) {
            case FIXED_AMOUNT:
                FixedAmountDiscountPolicy fixedPolicy = (FixedAmountDiscountPolicy) policy;
                return fixedPolicy.getDiscountAmount();
                
            case PERCENT_DISCOUNT:
                PercentDiscountPolicy percentPolicy = (PercentDiscountPolicy) policy;
                return (int) (baseAmount * percentPolicy.getDiscountRate());
                
            case NONE_DISCOUNT:
            default:
                return 0;
        }
    }

    public static void checkMaxDiscountAmount(int baseAmount, int afterDiscountAmount) {
        // 할인 금액 계산
        int discountAmount = baseAmount - afterDiscountAmount;
        
        // 할인 금액이 0보다 작으면 잘못된 할인 금액입니다.
        if (discountAmount < 0) {
            throw new RuntimeException("잘못된 할인 금액입니다. 할인 금액: " + discountAmount);
        }

        // 최대 할인 금액 초과 체크
        if (discountAmount > MAX_DISCOUNT_AMOUNT) {
            throw new RuntimeException("최대 할인 금액을 초과했습니다. 최대 할인 금액: " + discountAmount);
        }
    }
}

