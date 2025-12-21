package apec.test6.payment;

import apec.test6.Customer;
import apec.test6.Order;
import apec.test6.payment.intf.PaymentGateway;

/**
 * 지갑(암호화폐) 결제를 처리하는 PaymentGateway 구현체
 * PaymentGateway 인터페이스를 구현하여 지갑 결제 로직을 캡슐화합니다.
 */
public class WalletPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order) {
        Customer customer = order.getCustomer();
        
        String walletAddress = customer.getWalletAddress();
        System.out.println("지갑 결제 처리: " + walletAddress + " " + customer.getName());
    }
}
