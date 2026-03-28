package ch05_message_interface.after.claude6_3.enums;

public enum MembershipLevel {
    GOLD,
    SILVER,
    BRONZE;

    public int getDiscountRate() {
        return switch (this) {
            case GOLD -> 10;
            case SILVER -> 5;
            case BRONZE -> 0;
        };
    }
}
