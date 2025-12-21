package test.claude6_3;

import apec.test3.coupon.CouponType;

public class Coupon {
    private CouponType type; // 쿠폰 타입
    private int amount; // 할인 금액
    private int discountRate; // 할인 비율

    public CouponType getType() {
        return type;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public int getAmount() {
        return amount;
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
        if (getType() == CouponType.FIXED) {
            return Math.max(0, total - getAmount()); // 최소 0원 보장
        } else {
            // discountRate는 백분율 (예: 10 = 10%)
            return (int)(total * (1 - getDiscountRate() / 100.0));
        }
    }
}
