package test.claude6;

import test.claude6.policy.DeliveryFeePolicy;

public class Delivery {
    private int fee; // 기본 배송비
    private DeliveryFeePolicy deliveryFeePolicy; // 배송비 정책

    public Delivery(int fee, DeliveryFeePolicy deliveryFeePolicy) {
        this.fee = fee;
        this.deliveryFeePolicy = deliveryFeePolicy;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public DeliveryFeePolicy getDeliveryFeePolicy() {
        return deliveryFeePolicy;
    }

    public int calculateOrderFee(Order order) {
        return deliveryFeePolicy.calculateFee(order.getCustomer(), fee);
    }
}
