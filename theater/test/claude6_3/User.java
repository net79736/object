package test.claude6_3;

public class User {
    private MembershipLevel membershipLevel;
    private boolean isFirstPurchase;
    private Coupon coupon;

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
}
