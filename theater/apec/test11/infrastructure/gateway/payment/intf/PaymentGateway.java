package apec.test11.infrastructure.gateway.payment.intf;

import apec.test11.domain.order.dto.PaymentInfo;
import apec.test11.interfaces.api.dto.order.response.PaymentResponse;

public interface PaymentGateway {
    PaymentResponse process(PaymentInfo paymentInfo);
}

