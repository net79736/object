package ch08_shopping_system.after.apec.payment_order_point.coupon;

/**
 * 쿠폰 타입을 나타내는 enum
 * 
 * FIXED: 고정 금액 할인 (예: 1000원 할인)
 * PERCENT: 비율 할인 (예: 10% 할인)
 */
public enum CouponType {
    FIXED,    // 고정 금액 할인
    PERCENT;  // 비율 할인
}

