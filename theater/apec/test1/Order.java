package apec.test1;

import java.util.List;
import common.Money;

public class Order {

    private Customer customer;
    private List<OrderItem> items;
    private Money amount;
    private Delivery delivery;
    
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

    public Money getTotalAmount() {
        return amount.plus(delivery.getFee());
    }

    /**
     * 고객 등급에 따라 배송비를 계산합니다.
     */
    public void calculateDeliveryFee() {
        delivery.calculateDeliveryFee(customer);
    }

    /**
     * 주문상품의 재고를 감소시킵니다.
     */
    public void reverseStock() {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            product.decreaseStock(item.getQuantity());
        }
    }
}
