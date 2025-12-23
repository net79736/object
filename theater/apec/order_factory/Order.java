package apec.test8;

import java.util.List;

import apec.test8.policy.intf.DiscountPolicy;
import common.Money;

public class Order {
    private Customer customer;
    private List<OrderItem> items;
    private DiscountPolicy discountPolicy;

    public Order(Customer customer, List<OrderItem> items, DiscountPolicy discountPolicy) {
        this.customer = customer;
        this.items = items;
        this.discountPolicy = discountPolicy;
    }

    public void reserveStock() {
        for (OrderItem item : items) {
            item.reserveStock();  // OrderItem이 처리
        }
    }

    public Money calculateTotalPrice() {
        Money total = Money.ZERO;
        for (OrderItem item : items) {
            total = total.plus(Money.wons(item.getProduct().getPrice()).times(item.getQuantity()));
        }
        return discountPolicy.applyDiscount(total);
    }
}
