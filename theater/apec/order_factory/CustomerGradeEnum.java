package apec.order_factory;

public enum CustomerGradeEnum {
    VIP,
    GOLD,
    NORMAL;

    public int getDiscountRate() {
        return switch (this) {
            case VIP -> 10; // VIP 회원
            case GOLD -> 5; // 골드 회원
            case NORMAL -> 0; // 일반 회원
            default -> 0; // 일반 회원
        };
    }
}
