package apec.test5.notification;

import apec.test5.common.User;
import apec.test5.notification.intf.NotificationSender;

/**
 * 이메일 알림 전송 구현체
 * 
 * 사용자의 이메일 주소로 알림을 전송합니다.
 */
public class EmailSender implements NotificationSender {
    @Override
    public void send(User user, String message) {
        String email = user.getEmail();
        System.out.println("Sending email to " + email + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isEmailEnabled();
    }
}

