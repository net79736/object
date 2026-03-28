package ch04_solid_principles.after.apec.notification.sender;

import ch04_solid_principles.after.apec.notification.NotificationSettings;import ch04_solid_principles.after.apec.notification.User;import ch04_solid_principles.after.apec.notification.info.PushNotificationInfo;import ch04_solid_principles.after.apec.notification.info.intf.NotificationInfo;import ch04_solid_principles.after.apec.notification.sender.intf.NotificationSender;
/**
 * 푸시 알림 전송자
 * 타입 안전하게 User에서 디바이스 토큰을 추출하여 전송합니다.
 */
public class PushSender implements NotificationSender {
    private final NotificationInfo notificationInfo = new PushNotificationInfo();

    @Override
    public void send(User user, String message) {
        // NotificationInfo 어댑터를 통해 디바이스 토큰 추출 (타입 안전)
        String deviceToken = notificationInfo.extractInfo(user);
        // 푸시 알림 전송
        System.out.println("Sending push to " + deviceToken + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isPushEnabled();
    }
}
