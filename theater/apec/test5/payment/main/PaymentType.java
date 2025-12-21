package apec.test5.payment.main;

public enum PaymentType {
    CREDIT_CARD, // 신용카드
    ACCOUNT_TRANSFER, // 계좌이체
    POINT; // 포인트

    public int getDiscountRate() {
        return switch (this) {
            case CREDIT_CARD -> 10;
            case ACCOUNT_TRANSFER -> 5;
            case POINT -> 0;
        };
    }
}
