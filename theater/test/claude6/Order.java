package test.claude6;

import java.util.List;

public class Order {

    private Customer customer;
    private Delivery delivery;
    private List<OrderItem> items;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getTotalAmount() {
        return items.stream().mapToInt(item -> item.getProduct().getStock().getQuantity() * item.getQuantity()).sum();
    }
}
