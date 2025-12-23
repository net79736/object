package apec.payment_order_point.common;

import java.util.ArrayList;
import java.util.List;

import apec.payment_order_point.coupon.Coupon;
import apec.payment_order_point.discount.CouponDiscountPolicy;
import apec.payment_order_point.discount.GradeDiscountPolicy;
import apec.payment_order_point.discount.intf.DiscountPolicy;
import apec.payment_order_point.notification.NotificationSettings;
import apec.payment_order_point.notification.intf.NotificationSender;
import apec.payment_order_point.point.Point;

/**
 * 사용자 클래스
 * 
 * 사용자의 포인트, 멤버십 등급, 쿠폰, 알림 설정 등을 관리합니다.
 * "묻지 말고 시켜라" 원칙에 따라 사용자가 자신의 알림을 전송합니다.
 */
public class User {
    private String email;
    private String phone;
    private String deviceToken;
    // 멤버십 등급
    private MembershipLevel membershipLevel;
    // 포인트
    private Point point;
    // 쿠폰
    private Coupon coupon;
    // 알림 설정
    private NotificationSettings notificationSettings;
    // 알림 전송 객체
    private List<NotificationSender> notificationSenders;

    public User(Point point,
                String email,
                String phone,
                String deviceToken,
                MembershipLevel membershipLevel, 
                Coupon coupon,
                NotificationSettings notificationSettings,
                List<NotificationSender> notificationSenders
    ) {
        this.point = point;
        this.email = email;
        this.phone = phone;
        this.deviceToken = deviceToken;
        this.membershipLevel = membershipLevel;
        this.coupon = coupon;
        this.notificationSettings = notificationSettings;
        this.notificationSenders = notificationSenders;
    }

    public Point getPoint() {
        return point;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public NotificationSettings getNotificationSettings() {
        return notificationSettings;
    }

    /**
     * 포인트를 증가시킵니다.
     * @param point 증가시킬 포인트
     */
    public void increasePoint(int point) {
        this.point.increase(point);
    }

    /**
     * 포인트를 감소시킵니다.
     * @param point 감소시킬 포인트
     */
    public void decreasePoint(int point) {
        this.point.decrease(point);
    }

    /**
     * 최대 포인트 충전 방지에 대한 에러 처리
     * @param point 최대 포인트 충전 방지에 대한 에러 처리
     */
    public void checkMaxPoint(int point) {
        this.point.checkMaxPoint(point);
    }

    /**
     * 쿠폰이 있는지 확인합니다.
     * @return 쿠폰이 있는지 여부
     */
    public boolean hasCoupon() {
        return coupon != null;
    }

    /**
     * 사용자의 모든 할인 정책을 적용하여 최종 금액을 계산합니다.
     * "묻지 말고 시켜라" 원칙: User가 자신의 할인을 계산합니다.
     * 
     * @param total 초기 총 금액
     * @return 모든 할인 적용 후 금액
     */
    public int calculateFinalTotal(int total) {
        List<DiscountPolicy> policies = createDiscountPolicies();
        
        int finalTotal = total;
        for (DiscountPolicy policy : policies) {
            finalTotal = policy.applyDiscount(finalTotal);
        }
        
        return finalTotal;
    }

    /**
     * 사용자의 할인 정책 목록을 생성합니다.
     * 등급 할인 -> 쿠폰 할인 순서로 적용됩니다.
     * 
     * @return 할인 정책 목록
     */
    public List<DiscountPolicy> createDiscountPolicies() {
        List<DiscountPolicy> policies = new ArrayList<>();
        
        // 등급별 할인
        policies.add(new GradeDiscountPolicy(membershipLevel));
        
        // 쿠폰 할인
        if (hasCoupon()) {
            policies.add(new CouponDiscountPolicy(coupon));
        }
        
        return policies;
    }

    /**
     * NotificationSettings에 따라 활성화된 sender만을 사용하여 알림을 전송합니다.
     * 
     * DIP 원칙에 따라 User는 구체적인 sender 타입을 알 필요 없이,
     * NotificationSender 인터페이스의 isEnabled 메서드를 통해 필터링합니다.
     * 각 sender는 User 객체에서 자신에게 필요한 정보(이메일, 전화번호, 디바이스 토큰)를 추출합니다.
     * 
     * @param message 전송할 메시지
     */
    public void sendNotification(String message) {
        notificationSenders.stream()
                .filter(sender -> sender.isEnabled(notificationSettings))
                .forEach(sender -> sender.send(this, message));
    }

    /**
     * 사용자의 포인트 잔액을 조회합니다.
     * @return 포인트 잔액
     */
    public int getPointBalance() {
        return point.getPoint();
    }

    /**
     * 포인트 잔액 검증
     * @param point 검증할 포인트
     * @throws RuntimeException 포인트 잔액이 부족할 경우
     */
    public void checkPointBalance(int point) {
        if (point > getPointBalance()) {
            throw new RuntimeException("포인트 잔액이 부족합니다. 포인트: " + point + ", 포인트 잔액: " + getPointBalance());
        }
    }
}
