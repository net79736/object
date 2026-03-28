package ch04_solid_principles.after.apec.discount.enums;

public enum MembershipLevelEnum {
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
