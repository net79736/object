package ch04_solid_principles.after.apec.order;

import java.time.LocalDateTime;

import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;import common.Money;

public class Order {
    private Customer customer; // 고객
    private Money amount; // 주문 금액
    private PaymentInfo paymentInfo;  // 결제 수단별 정보를 담는 객체
    private OrderStatus orderStatus; // 주문 상태
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    /**
     * 주문의 결제 수단 타입을 반환합니다.
     * 
     * PaymentInfo를 통해 접근하지만, PaymentInfo는 Order의 일부이므로
     * 실용적으로 허용 가능한 접근 방식입니다.
     * 단일 진실의 원천(Single Source of Truth) 원칙을 준수합니다.
     * 
     * @return 결제 수단 타입
     */
    public PaymentTypeEnum paymentType() {
        return paymentInfo.getPaymentType();
    }

    /**
     * 주문 상태와 결제 일시를 설정합니다.
     * @param status 주문 상태
     */
    public void updateOrderStatusWithPaymentDate(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.paymentDate = LocalDateTime.now();
    }
}
