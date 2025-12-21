package apec.test6.payment.info;

import apec.test6.payment.intf.PaymentInfo;

/**
 * 카드 결제에 필요한 정보를 담는 클래스
 * Customer에서 카드 관련 정보를 분리하여 결제 수단별로 독립적으로 관리합니다.
 */
public class CardPaymentInfo implements PaymentInfo {
    private final String cardNumber;
    private final String cvv;
    private final String cardholderName;

    public CardPaymentInfo(String cardNumber, String cvv, String cardholderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardholderName = cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    @Override
    public String getPaymentMethod() {
        return "CARD";
    }
}

