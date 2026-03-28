package ch07_design_patterns.after.notification.domain;

/**
 * 사용자 도메인 엔티티
 * 사용자의 기본 정보와 알림 설정을 관리합니다.
 * 알림 전송 책임은 NotificationService에 위임합니다.
 * 
 * 도메인 모델링 관점에서 User는 자신의 기본 정보(이메일, 전화번호 등)를 직접 보유합니다.
 */
public class User {
    private String email; // 이메일 주소
    private String phone; // 전화번호
    private String deviceToken; // 푸시 알림용 디바이스 토큰
    private NotificationSettings notificationSettings; // 알림 설정

    public User(String email, String phone, String deviceToken, NotificationSettings notificationSettings) {
        this.email = email;
        this.phone = phone;
        this.deviceToken = deviceToken;
        this.notificationSettings = notificationSettings;
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

    public NotificationSettings getNotificationSettings() {
        return notificationSettings;
    }
}