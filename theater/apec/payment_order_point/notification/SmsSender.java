package apec.test5.notification;

import apec.test5.common.User;
import apec.test5.notification.intf.NotificationSender;

/**
 * SMS 알림 전송 구현체
 * 
 * 사용자의 전화번호로 SMS 알림을 전송합니다.
 */
public class SmsSender implements NotificationSender {
    @Override
    public void send(User user, String message) {
        String phone = user.getPhone();
        System.out.println("Sending SMS to " + phone + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isSmsEnabled();
    }
}

