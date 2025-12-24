package apec.order;

import java.time.LocalDateTime;

import apec.order.payment.intf.PaymentInfo;
import common.Money;

public class Order {
    private Customer customer; // 고객
    private Money amount; // 주문 금액
    private PaymentInfo paymentInfo;  // 결제 수단별 정보를 담는 객체
    private String status; // 주문 상태
    private LocalDateTime paymentDate; // 결제 일시

    public Order(Customer customer, Money amount, PaymentInfo paymentInfo) {
        this.customer = customer;
        this.amount = amount;
        this.paymentInfo = paymentInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Money getAmount() {
        return amount;
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
