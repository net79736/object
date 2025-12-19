package test.claude6_3.enums;

public enum CouponType {
    FIXED, // 고정 금액 할인
    PERCENT; // 비율 할인

    public int getDiscountRate() {
        return switch (this) {
            case FIXED -> 10;
            case PERCENT -> 5;
        };
    }
}
