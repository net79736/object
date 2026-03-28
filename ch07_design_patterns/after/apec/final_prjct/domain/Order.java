package ch07_design_patterns.after.apec.final_prjct.domain;

import static ch07_design_patterns.after.apec.final_prjct.common.DiscountPolicyOptimizer.checkMaxDiscountAmount;
import java.util.Arrays;
import java.util.List;

import ch07_design_patterns.after.apec.final_prjct.common.DiscountPolicyChain;import ch07_design_patterns.after.apec.final_prjct.common.DiscountPolicyOptimizer;import ch07_design_patterns.after.apec.final_prjct.repository.policy.intf.DiscountPolicy;
public class Order {
    private Customer customer;
    private List<OrderItem> items;
    private List<DiscountPolicy> discountPolicies;

    public Order(Customer customer, List<OrderItem> items, DiscountPolicy... discountPolicies) {
        this.customer = customer;
        this.items = items;
        this.discountPolicies = Arrays.asList(discountPolicies);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public List<DiscountPolicy> getDiscountPolicies() {
        return discountPolicies;
    }

    /**
     * 할인 정책을 적용한 총 금액을 반환합니다.
     * 묻지 말고 시켜라 원칙: User가 자신의 할인을 계산합니다.
     * 동일한 타입의 할인 정책이 있으면 더 높은 할인을 주는 정책만 적용합니다.
     * 
     * @param total 초기 총 금액
     * @return 모든 할인 적용 후 금액
     */
    public int getTotalAfterDiscounts(int total) {
        // 동일 타입의 정책 중 더 높은 할인을 주는 정책만 선택
        List<DiscountPolicy> optimizedPolicies = DiscountPolicyOptimizer.optimize(discountPolicies, total);
        
        // 최적화된 정책들을 체인에 추가하여 순차적으로 적용
        DiscountPolicyChain chain = new DiscountPolicyChain();
        for (DiscountPolicy policy : optimizedPolicies) {
            chain.addPolicy(policy);
        }
    
        // 최대 할인 금액 초과 체크
        int afterDiscountAmount = chain.applyAll(total);
        checkMaxDiscountAmount(total, afterDiscountAmount);

        return afterDiscountAmount;
    }

}
