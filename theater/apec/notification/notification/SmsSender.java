package apec.notification.notification;

import apec.notification.NotificationSettings;
import apec.notification.User;
import apec.notification.notification.intf.NotificationSender;

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
