package apec.payment_order_point.main;

/**
 * 결제 수단 타입
 * 
 * 할인 정책은 PaymentDiscountPolicy로 분리되어 있어,
 * 이 enum은 결제 수단 타입만을 나타냅니다.
 */
public enum PaymentType {
    CREDIT_CARD, // 신용카드
    ACCOUNT_TRANSFER, // 계좌이체
    POINT; // 포인트
}
