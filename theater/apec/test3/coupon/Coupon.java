package apec.test3.coupon;

/**
 * 쿠폰 클래스
 * 고정 금액 할인 또는 비율 할인을 제공합니다.
 */
public class Coupon {
    private CouponType type; // 쿠폰 타입
    private int amount; // 할인 금액 (고정 금액 할인 시 사용)
    private int discountRate; // 할인 비율 (비율 할인 시 사용, 백분율)

    public Coupon(CouponType type, int amount, int discountRate) {
        this.type = type;
        this.amount = amount;
        this.discountRate = discountRate;
    }

    public CouponType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    /**
     * 쿠폰 할인 적용
     * 고정 금액 할인: 총 금액에서 할인 금액 빼기
     * 비율 할인: 총 금액에서 할인 비율 곱하기
     * 
     * @param total 총 금액
     * @return 할인 후 금액
     */
    public int applyDiscountAmount(int total) {
        if (type == CouponType.FIXED) {
            return Math.max(0, total - amount); // 최소 0원 보장
        } else {
            // discountRate는 백분율 (예: 10 = 10%)
            return (int)(total * (1 - discountRate / 100.0));
        }
    }
}

