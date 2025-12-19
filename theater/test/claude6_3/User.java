package test.claude6_3;

public class User {
    private MembershipLevel membershipLevel; // 사용자 등급
    private boolean isFirstPurchase; // 첫 구매 여부
    private Coupon coupon; // 쿠폰

    public MembershipLevel getMembershipLevel() {
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

    /**
     * 사용자 등급별 할인 계산
     * @param total
     * @return 할인 후 금액
     */
    public int calculateDiscountedTotal(int total) {
        if (getMembershipLevel() == MembershipLevel.GOLD) {
            return (int)(total * 0.9); // 10% 할인
        } else if (getMembershipLevel() == MembershipLevel.SILVER) {
            return (int)(total * 0.95); // 5% 할인
        }
        return total;
    }

    public int calculateFirstPurchaseDiscountedTotal(int total) {
        if (isFirstPurchase()) {
            return (int)(total * 0.9); // 10% 추가 할인
        }
        return total;
    }

    public int applyCouponDiscount(int total) {
        int result = total;
        if (hasCoupon()) {            
            coupon.applyDiscountAmount(result);
        }
        return total;
    }
}
