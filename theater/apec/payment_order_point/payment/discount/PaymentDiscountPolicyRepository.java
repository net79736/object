package apec.payment_order_point.payment.discount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import apec.payment_order_point.main.PaymentType;
import apec.payment_order_point.payment.discount.intf.PaymentDiscountPolicy;

/**
 * 결제 수단별 할인 정책 저장소
 * 
 * 결제 수단별 할인 정책을 관리하고 조회합니다.
 * 이벤트별로 다른 할인 정책을 적용할 수 있도록 설계되었습니다.
 * 
 * 실제 운영 환경에서는 데이터베이스나 설정 파일에서 동적으로 로드할 수 있습니다.
 */
public class PaymentDiscountPolicyRepository {
    private final Map<PaymentType, PaymentDiscountPolicy> policies;
    
    public PaymentDiscountPolicyRepository() {
        this.policies = new HashMap<>();
        initializeDefaultPolicies();
    }
    
    /**
     * 기본 할인 정책을 초기화합니다.
     * 실제 운영 환경에서는 데이터베이스나 설정 파일에서 로드할 수 있습니다.
     */
    private void initializeDefaultPolicies() {
        // 기본 할인 정책 설정
        policies.put(PaymentType.CREDIT_CARD, new RatePaymentDiscountPolicy(PaymentType.CREDIT_CARD, 10));
        policies.put(PaymentType.ACCOUNT_TRANSFER, new RatePaymentDiscountPolicy(PaymentType.ACCOUNT_TRANSFER, 5));
        policies.put(PaymentType.POINT, new NonePaymentDiscountPolicy(PaymentType.POINT));
    }
    
    /**
     * 결제 수단별 할인 정책을 조회합니다.
     * 
     * @param paymentType 결제 수단 타입
     * @return 할인 정책 (없으면 Optional.empty())
     */
    public Optional<PaymentDiscountPolicy> findByPaymentType(PaymentType paymentType) {
        return Optional.ofNullable(policies.get(paymentType));
    }
    
    /**
     * 결제 수단별 할인 정책을 등록합니다.
     * 이벤트별로 다른 할인 정책을 적용할 수 있습니다.
     * 
     * @param policy 할인 정책
     */
    public void register(PaymentDiscountPolicy policy) {
        policies.put(policy.getPaymentType(), policy);
    }
    
    /**
     * 모든 할인 정책을 초기화하고 새로운 정책으로 교체합니다.
     * 이벤트 시작 시 사용할 수 있습니다.
     * 
     * @param newPolicies 새로운 할인 정책 맵
     */
    public void replaceAll(Map<PaymentType, PaymentDiscountPolicy> newPolicies) {
        policies.clear();
        policies.putAll(newPolicies);
    }
    
    /**
     * 특정 결제 수단의 할인 정책을 제거합니다.
     * 
     * @param paymentType 결제 수단 타입
     */
    public void remove(PaymentType paymentType) {
        policies.remove(paymentType);
    }
}

