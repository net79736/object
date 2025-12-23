package apec.stock_delivery;

import common.Money;

public class Delivery {

    private int fee; // 기본 배송비

    public Delivery(int fee) {
        this.fee = fee;
    }

    public Money getFee() {
        return Money.wons(fee);
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void calculateFee(Customer customer) {
        if (customer.isVipCustomer()) {
            setFee(0);
        } else {
            // TODO: 배송비 계산 로직 구현
        }
    }
    
}
