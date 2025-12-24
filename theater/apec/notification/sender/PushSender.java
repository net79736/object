package apec.notification.sender;

import apec.notification.NotificationSettings;
import apec.notification.User;
import apec.notification.info.PushNotificationInfo;
import apec.notification.info.intf.NotificationInfo;
import apec.notification.sender.intf.NotificationSender;

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
