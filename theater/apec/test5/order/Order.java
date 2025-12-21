package apec.test5.order;

import java.util.List;
import apec.test5.common.User;
import apec.test5.payment.main.PaymentType;

public class Order {
    private List<OrderItem> items;
    private User user;
    private PaymentType paymentType;

    public Order(List<OrderItem> items, User user, PaymentType paymentType) {
        this.items = items;
        this.user = user;
        this.paymentType = paymentType;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getFinalTotalAmount(int totalAmount) {
        return user.calculateFinalTotal(totalAmount);
    }
}
