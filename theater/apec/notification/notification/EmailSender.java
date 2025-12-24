package apec.notification.notification;

import apec.notification.NotificationSettings;
import apec.notification.User;
import apec.notification.notification.intf.NotificationSender;

public class EmailSender implements NotificationSender {
    @Override
    public void send(User user, String message) {
        // 이메일 주소 추출
        String email = user.getEmail();
        // 이메일 전송
        System.out.println("Sending email to " + email + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isEmailEnabled();
    }
}
