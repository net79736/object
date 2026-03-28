package ch05_message_interface.after.claude6_1.policy;

import ch05_message_interface.after.claude6_1.Customer;
/**
 * 배송비 계산 정책 인터페이스
 * 전략 패턴을 사용하여 다양한 배송비 계산 정책을 적용할 수 있습니다.
 */
public interface DeliveryFeePolicy {
    /**
     * 고객 정보를 기반으로 배송비를 계산합니다.
     * 
     * @param customer 고객 정보
     * @param baseFee 기본 배송비
     * @return 계산된 배송비
     */
    int calculateFee(Customer customer, int baseFee);
}

