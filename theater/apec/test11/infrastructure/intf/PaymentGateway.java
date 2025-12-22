package apec.test11.infrastructure.intf;

import apec.test11.paymentinfo.PaymentInfo;

public interface PaymentGateway {
    void processPayment(PaymentInfo info);
}
