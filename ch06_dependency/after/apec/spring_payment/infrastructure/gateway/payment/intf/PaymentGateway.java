package ch06_dependency.after.apec.spring_payment.infrastructure.gateway.payment.intf;

import ch06_dependency.after.apec.spring_payment.domain.order.dto.PaymentInfo;import ch06_dependency.after.apec.spring_payment.interfaces.api.dto.order.response.PaymentResponse;
public interface PaymentGateway {
    PaymentResponse process(PaymentInfo paymentInfo);
}

