package apec.test6.payment;

import apec.test6.Customer;
import apec.test6.Order;
import apec.test6.payment.intf.PaymentGateway;

/**
 * 계좌이체 결제를 처리하는 PaymentGateway 구현체
 * PaymentGateway 인터페이스를 구현하여 계좌이체 결제 로직을 캡슐화합니다.
 */
public class AccountPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order) {
        Customer customer = order.getCustomer();
        
        String accountNumber = customer.getAccountNumber();
        String bankCode = customer.getBankCode();
        System.out.println("계좌이체 결제 처리: " + accountNumber + " " + bankCode + " " + customer.getName());
    }
}
