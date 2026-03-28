package ch04_solid_principles.after.apec.notification.sender;

import ch04_solid_principles.after.apec.notification.NotificationSettings;import ch04_solid_principles.after.apec.notification.User;import ch04_solid_principles.after.apec.notification.info.SmsNotificationInfo;import ch04_solid_principles.after.apec.notification.info.intf.NotificationInfo;import ch04_solid_principles.after.apec.notification.sender.intf.NotificationSender;
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
