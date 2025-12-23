package apec.spring_payment.infrastructure.gateway.payment.intf;

import apec.spring_payment.domain.order.dto.PaymentInfo;
import apec.spring_payment.interfaces.api.dto.order.response.PaymentResponse;

public interface PaymentGateway {
    PaymentResponse process(PaymentInfo paymentInfo);
}

