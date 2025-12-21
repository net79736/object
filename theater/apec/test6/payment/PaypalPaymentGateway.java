package apec.test6.payment;

import apec.test6.Customer;
import apec.test6.Order;
import apec.test6.payment.intf.PaymentGateway;

/**
 * PayPal 결제를 처리하는 PaymentGateway 구현체
 * PaymentGateway 인터페이스를 구현하여 PayPal 결제 로직을 캡슐화합니다.
 */
public class PaypalPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order) {
        Customer customer = order.getCustomer();
        
        String email = customer.getEmail();
        System.out.println("PayPal 결제 처리: " + email + " " + customer.getName());
    }
}
