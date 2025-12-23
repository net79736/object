package apec.stock_delivery;

import java.util.List;

import common.Money;

public class Order {

    private Customer customer; // 고객
    private List<OrderItem> items; // 주문 상품
    private Money amount; // 총 주문 금액
    private Delivery delivery; // 배송 정보
    
    public Order(Customer customer, List<OrderItem> items, Money amount, Delivery delivery) {
        this.customer = customer;
        this.items = items;
        this.amount = amount;
        this.delivery = delivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Money getAmount() {
        return amount;
    }

    /**
     * 총 주문 금액을 계산합니다.
     * @return 총 주문 금액
     */
    public Money getTotalAmount() {
        return amount.plus(delivery.getFee()); // 총 주문 금액 = 주문 금액 + 배송비
    }

    /**
     * 고객 등급에 따라 배송비를 계산합니다.
     */
    public void calculateDeliveryFee() {
        delivery.calculateFee(customer);
    }

    /**
     * 주문상품의 재고를 감소시킵니다.
     */
    public void reverseStock() {
        for (OrderItem item : items) {
            item.decreaseProductStock(); // 주문 상품의 재고를 감소시킵니다.
        }
    }

    public void processPay() {
        customer.pay(getTotalAmount());
    }
}
