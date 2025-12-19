package test.claude6;

public enum Grade {
    VIP,
    GOLD,
    SILVER,
    BRONZE;

    public int getLevel() {
        return switch (this) {
            case VIP -> 3;
            case GOLD -> 2;
            case SILVER -> 1;
            case BRONZE -> 0;
        };
    }

    // Grade.java에 추가
    public boolean isVip() {
        return this == VIP;
    }
}
