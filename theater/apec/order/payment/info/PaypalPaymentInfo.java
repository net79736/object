package apec.order.payment.info;

import apec.order.PaymentTypeEnum;
import apec.order.payment.intf.PaymentInfo;

/**
 * PayPal 결제에 필요한 정보를 담는 클래스
 */
public class PaypalPaymentInfo implements PaymentInfo {
    private final String email;
    private final PaymentTypeEnum paymentType; // 결제 수단

    public PaypalPaymentInfo(String email, PaymentTypeEnum paymentType) {
        this.email = email;
        this.paymentType = paymentType;
    }

    public String getEmail() {
        return email;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    @Override
    public String getPaymentMethod() {
        return PaymentTypeEnum.PAYPAL.name();
    }
}

