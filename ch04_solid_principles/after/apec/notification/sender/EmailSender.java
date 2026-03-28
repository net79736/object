package ch04_solid_principles.after.apec.notification.sender;

import ch04_solid_principles.after.apec.notification.NotificationSettings;import ch04_solid_principles.after.apec.notification.User;import ch04_solid_principles.after.apec.notification.info.EmailNotificationInfo;import ch04_solid_principles.after.apec.notification.info.intf.NotificationInfo;import ch04_solid_principles.after.apec.notification.sender.intf.NotificationSender;
/**
 * 이메일 알림 전송자
 * 타입 안전하게 User에서 이메일 주소를 추출하여 전송합니다.
 */
public class EmailSender implements NotificationSender {
    private final NotificationInfo notificationInfo = new EmailNotificationInfo();

    @Override
    public void send(User user, String message) {
        // NotificationInfo 어댑터를 통해 이메일 주소 추출 (타입 안전)
        String email = notificationInfo.extractInfo(user);
        // 이메일 전송
        System.out.println("Sending email to " + email + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isEmailEnabled();
    }
}
