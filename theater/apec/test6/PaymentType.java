package apec.test6;

/**
 * 결제 수단 타입을 나타내는 enum
 * 새로운 결제 수단 추가 시 여기에만 추가하면 됨 (OCP 준수)
 */
public enum PaymentType {
    CARD("카드"),
    BANK_TRANSFER("계좌이체"),
    PAYPAL("PayPal"),
    WALLET("지갑");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

