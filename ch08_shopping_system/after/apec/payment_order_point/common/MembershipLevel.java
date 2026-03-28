package ch08_shopping_system.after.apec.payment_order_point.common;

/**
 * 회원 등급을 나타내는 enum
 * 각 등급별 할인율을 제공합니다.
 */
public enum MembershipLevel {
    NORMAL(0),   // 일반 회원: 0% 할인
    SILVER(5),   // 실버 회원: 5% 할인
    GOLD(10),    // 골드 회원: 10% 할인
    VIP(15);     // VIP 회원: 15% 할인

    private final int discountRate;

    MembershipLevel(int discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 해당 등급의 할인율을 반환합니다.
     * 
     * @return 할인율 (퍼센트)
     */
    public int getDiscountRate() {
        return discountRate;
    }
}

