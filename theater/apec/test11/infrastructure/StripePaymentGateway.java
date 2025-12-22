package apec.test11.infrastructure;

import apec.test11.infrastructure.intf.PaymentGateway;
import apec.test11.paymentinfo.PaymentInfo;

public class StripePaymentGateway implements PaymentGateway {
    @Override
    public void processPayment(PaymentInfo info) {
        // Stripe API 호출
        System.out.println("Stripe API 호출: " + info);
    }
}
