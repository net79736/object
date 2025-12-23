package apec.test11.infrastructure.gateway.payment.impl;

import apec.test11.domain.order.dto.PaymentInfo;
import apec.test11.infrastructure.gateway.payment.intf.PaymentGateway;
import apec.test11.interfaces.api.dto.order.response.PaymentResponse;

public class PayPalPaymentGateway implements PaymentGateway {
    @Override
    public PaymentResponse process(PaymentInfo paymentInfo) {
        // PayPal API 호출
        System.out.println("PayPal API 호출: " + paymentInfo);
        return new PaymentResponse(true);
    }
}

