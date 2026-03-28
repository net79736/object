package ch07_design_patterns.after.apec.final_prjct.enums;

/**
 * 회원 등급을 나타내는 enum
 * 각 등급별 할인율을 제공합니다.
 */
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

    public boolean isVip() {
        return this == CustomerGradeEnum.VIP;
    }

    public boolean isGold() {
        return this == CustomerGradeEnum.GOLD;
    }

    public boolean isNormal() {
        return this == CustomerGradeEnum.NORMAL;
    }
}
