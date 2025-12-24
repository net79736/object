package apec.notification.sender;

import apec.notification.NotificationSettings;
import apec.notification.User;
import apec.notification.info.SmsNotificationInfo;
import apec.notification.info.intf.NotificationInfo;
import apec.notification.sender.intf.NotificationSender;

/**
 * SMS 알림 전송자
 * 타입 안전하게 User에서 전화번호를 추출하여 전송합니다.
 */
public class SmsSender implements NotificationSender {
    private final NotificationInfo notificationInfo = new SmsNotificationInfo();

    @Override
    public void send(User user, String message) {
        // NotificationInfo 어댑터를 통해 전화번호 추출 (타입 안전)
        String phone = notificationInfo.extractInfo(user);
        // 문자메시지 전송
        System.out.println("Sending sms to " + phone + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isSmsEnabled();
    }
}
