package apec.test6;

import java.time.LocalDateTime;
import common.Money;

public class Order {
    private Customer customer;
    private Money amount;
    private PaymentType paymentType;
    private String status;
    private LocalDateTime paymentDate;

    public Order(Customer customer, Money amount, PaymentType paymentType) {
        this.customer = customer;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Money getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    /**
     * 주문 상태와 결제 일시를 설정합니다.
     * @param status 주문 상태
     */
    public void setPaymentStatus(OrderStatus status) {
        this.status = status.name();
        this.paymentDate = LocalDateTime.now();
    }


}
