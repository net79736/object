package test.claude6_1.policy;

import test.claude6_1.Customer;

/**
 * VIP 고객 배송비 정책
 * VIP 고객은 배송비가 무료입니다.
 */
public class VipDeliveryFeePolicy implements DeliveryFeePolicy {
    
    @Override
    public int calculateFee(Customer customer, int baseFee) {
        if (customer != null && customer.isVipCustomer()) {
            return 0; // VIP 고객은 배송비 무료
        }
        return baseFee;
    }
}

