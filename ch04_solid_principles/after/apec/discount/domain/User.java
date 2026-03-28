package ch04_solid_principles.after.apec.discount.domain;

import ch04_solid_principles.after.apec.discount.enums.MembershipLevelEnum;
/**
 * 사용자 클래스
 * 사용자의 멤버십 등급, 첫 구매 여부, 쿠폰 정보를 관리합니다.
 * 
 * 책임:
 * - 사용자 정보 관리 (멤버십 등급, 첫 구매 여부, 쿠폰)
 * - 할인 정보 제공 (getter 메서드)
 * 
 * 할인 계산은 DiscountCalculator가 담당합니다.
 * 이렇게 분리함으로써 User는 자신의 정보만 관리하고,
 * 할인 계산 로직은 별도의 클래스에서 관리됩니다.
 */
public class User {
    private MembershipLevelEnum membershipLevel;
    private boolean isFirstPurchase;
    private Coupon coupon;

    public User(MembershipLevelEnum membershipLevel, boolean isFirstPurchase, Coupon coupon) {
        this.membershipLevel = membershipLevel;
        this.isFirstPurchase = isFirstPurchase;
        this.coupon = coupon;
    }

    public MembershipLevelEnum getMembershipLevel() {
        return membershipLevel;
    }
    
    public boolean isFirstPurchase() {
        return isFirstPurchase;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public boolean hasCoupon() {
        return coupon != null;
    }
}
