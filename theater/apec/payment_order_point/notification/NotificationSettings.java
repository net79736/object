package apec.payment_order_point.notification;

/**
 * 알림 설정 클래스
 * 
 * 사용자의 알림 수신 설정을 관리합니다.
 */
public class NotificationSettings {
    private final boolean emailEnabled;
    private final boolean smsEnabled;
    private final boolean pushEnabled;

    public NotificationSettings(boolean emailEnabled, boolean smsEnabled, boolean pushEnabled) {
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.pushEnabled = pushEnabled;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    public boolean isPushEnabled() {
        return pushEnabled;
    }
}

