package ch05_message_interface.after.claude6_1;

import java.util.List;

public class Order {

    private Customer customer; // 고객 정보
    private Delivery delivery; // 배송 정보
    private List<OrderItem> items; // 주문 상품 정보
    
    public Order(Customer customer, Delivery delivery, List<OrderItem> items) {
        this.customer = customer;
        this.delivery = delivery;
        this.items = items;
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

    public int getTotalAmount() {
        return items.stream().mapToInt(item -> item.getProduct().getStock().getQuantity() * item.getQuantity()).sum();
    }

    /**
     * 고객 등급에 따라 배송비를 계산합니다.
     * VIP 고객은 배송비가 무료입니다.
     * 묻지 말고 시켜라 원칙: Order가 자신의 배송비를 계산합니다.
     */
    public void calculateDeliveryFee() {
        delivery.setFee(delivery.calculateOrderFee(this)); // 배송비 계산
    }

    public void decreaseStock() {
        for (OrderItem item : items) {
            Product product = item.getProduct(); // 주문 상품
            product.decreaseStock(item.getQuantity()); // 재고 감소
        }
    }

    public void processPayment() {
        customer.pay(getTotalAmount());
    }
}
