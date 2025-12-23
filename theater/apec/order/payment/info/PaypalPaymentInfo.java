package apec.test6.payment.info;

import static apec.test6.PaymentTypeEnum.PAYPAL;
import apec.test6.payment.intf.PaymentInfo;

/**
 * PayPal 결제에 필요한 정보를 담는 클래스
 */
public class PaypalPaymentInfo implements PaymentInfo {
    private final String email;

    public PaypalPaymentInfo(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPaymentMethod() {
        return PAYPAL.name();
    }
}

