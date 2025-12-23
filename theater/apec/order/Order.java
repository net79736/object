package apec.order;

import java.time.LocalDateTime;

import apec.order.payment.intf.PaymentInfo;
import common.Money;

public class Order {
    private Customer customer;
    private Money amount;
    private PaymentTypeEnum paymentType;
    private PaymentInfo paymentInfo;  // 결제 수단별 정보를 담는 객체
    private String status;
    private LocalDateTime paymentDate;

    public Order(Customer customer, Money amount, PaymentTypeEnum paymentType, PaymentInfo paymentInfo) {
        this.customer = customer;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentInfo = paymentInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Money getAmount() {
        return amount;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
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
