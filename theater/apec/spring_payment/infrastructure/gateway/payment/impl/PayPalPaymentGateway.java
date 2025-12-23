package apec.spring_payment.infrastructure.gateway.payment.impl;

import apec.spring_payment.domain.order.dto.PaymentInfo;
import apec.spring_payment.infrastructure.gateway.payment.intf.PaymentGateway;
import apec.spring_payment.interfaces.api.dto.order.response.PaymentResponse;

public class PayPalPaymentGateway implements PaymentGateway {
    @Override
    public PaymentResponse process(PaymentInfo paymentInfo) {
        // PayPal API 호출
        System.out.println("PayPal API 호출: " + paymentInfo);
        return new PaymentResponse(true);
    }
}

