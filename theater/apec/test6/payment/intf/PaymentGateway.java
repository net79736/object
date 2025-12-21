package apec.test6.payment.intf;

import apec.test6.Order;

public interface PaymentGateway {
    void pay(Order order);
}
