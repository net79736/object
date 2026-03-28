package ch05_message_interface.after.claude6_1;

import ch04_solid_principles.after.apec.stock_delivery.Customer;import common.Money;
import ch05_message_interface.after.claude6_1.policy.DeliveryFeePolicy;
public class Delivery {
    private int fee; // 기본 배송비
    private DeliveryFeePolicy deliveryFeePolicy; // 배송비 정책

    public Delivery(int fee, DeliveryFeePolicy deliveryFeePolicy) {
        this.fee = fee;
        this.deliveryFeePolicy = deliveryFeePolicy;
    }

    public Money getFee() {
        return Money.wons(fee);
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

    public void calculateDeliveryFee(Customer customer) {
        if (customer.isVipCustomer()) {
            setFee(0);
        } else {
            // TODO: 배송비 계산 로직 구현
        }
    }
}
