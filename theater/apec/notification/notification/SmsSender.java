package apec.test4.notification;

import apec.test4.NotificationSettings;
import apec.test4.User;
import apec.test4.notification.intf.NotificationSender;

public class SmsSender implements NotificationSender {
    @Override
    public void send(User user, String message) {
        String phone = user.getPhone();
        System.out.println("Sending sms to " + phone + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isSmsEnabled();
    }
}
