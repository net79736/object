package apec.test6.payment;

import apec.test6.Order;
import apec.test6.payment.info.PaypalPaymentInfo;
import apec.test6.payment.intf.PaymentGateway;
import common.Money;

/**
 * PayPal 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: PayPal 결제의 구체적인 처리 방법
 */
public class PaypalPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order, apec.test6.payment.intf.PaymentInfo paymentInfo, Money amount) {
        PaypalPaymentInfo paypalInfo = (PaypalPaymentInfo) paymentInfo;
        
        String email = paypalInfo.getEmail();
        System.out.println("PayPal 결제 처리: " + email + " " + amount);
    }
}
