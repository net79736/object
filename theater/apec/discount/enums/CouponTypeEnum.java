package apec.discount.enums;

public enum CouponTypeEnum {
    FIXED, // 고정 금액 할인
    PERCENT; // 비율 할인

    public int getDiscountRate() {
        return switch (this) {
            case FIXED -> 10;
            case PERCENT -> 5;
        };
    }
}
