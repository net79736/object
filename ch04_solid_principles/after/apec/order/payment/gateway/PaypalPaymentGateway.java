package ch04_solid_principles.after.apec.order.payment.gateway;

import ch04_solid_principles.after.apec.order.Order;import ch04_solid_principles.after.apec.order.payment.info.PaypalPaymentInfo;import ch04_solid_principles.after.apec.order.payment.intf.PaymentGateway;import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;import common.Money;

/**
 * PayPal 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: PayPal 결제의 구체적인 처리 방법
 */
public class PaypalPaymentGateway implements PaymentGateway {
    @Override
    public void requestPayment(Order order, PaymentInfo paymentInfo, Money amount) {
        PaypalPaymentInfo paypalInfo = (PaypalPaymentInfo) paymentInfo;
        
        String email = paypalInfo.getEmail();
        System.out.println("PayPal 결제 처리: " + email + " " + amount);
    }
}
