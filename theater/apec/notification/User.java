package apec.notification;

/**
 * 사용자 도메인 엔티티
 * 사용자의 기본 정보와 알림 설정을 관리합니다.
 * 알림 전송 책임은 NotificationService에 위임합니다.
 */
public class User {
    private NotificationSettings notificationSettings; // 알림 설정
    private String email; // 이메일
    private String phone; // 전화번호
    private String deviceToken; // 디바이스 토큰

    public User(NotificationSettings notificationSettings,
                String email, String phone, String deviceToken) {
        this.notificationSettings = notificationSettings;
        this.email = email;
        this.phone = phone;
        this.deviceToken = deviceToken;
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
