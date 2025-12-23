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
            setFee(0); // VIP 고객은 배송비 무료
        } else {
            setFee(fee); // 기존 고객은 기본 배송비 적용
        }
    }
    
}
