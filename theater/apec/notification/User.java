package apec.notification;

import java.util.List;
import apec.notification.notification.intf.NotificationSender;

public class User {
    private List<NotificationSender> notificationSenders;   
    private NotificationSettings notificationSettings;
    private String email;
    private String phone;
    private String deviceToken;

    public User(List<NotificationSender> notificationSenders, NotificationSettings notificationSettings,
                String email, String phone, String deviceToken) {
        this.notificationSenders = notificationSenders;
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

    /**
     * NotificationSettings에 따라 활성화된 sender만을 사용하여 알림을 전송합니다.
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
}
