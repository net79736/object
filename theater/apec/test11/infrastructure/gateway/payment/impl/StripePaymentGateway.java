package apec.test11.infrastructure.gateway.payment.impl;

import apec.test11.domain.order.dto.PaymentInfo;
import apec.test11.infrastructure.gateway.payment.intf.PaymentGateway;
import apec.test11.interfaces.api.dto.order.response.PaymentResponse;

public class StripePaymentGateway implements PaymentGateway {

    @Override
    public PaymentResponse process(PaymentInfo paymentInfo) {
        // Stripe API 호출
        System.out.println("Stripe API 호출: " + paymentInfo);
        return new PaymentResponse(true);
    }
}

