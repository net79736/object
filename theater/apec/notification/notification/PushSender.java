package apec.notification.notification;

import apec.notification.NotificationSettings;
import apec.notification.User;
import apec.notification.notification.intf.NotificationSender;

public class PushSender implements NotificationSender {
    @Override
    public void send(User user, String message) {
        String deviceToken = user.getDeviceToken();
        System.out.println("Sending push to " + deviceToken + " with message: " + message);
    }
    
    @Override
    public boolean isEnabled(NotificationSettings settings) {
        return settings.isPushEnabled();
    }
}
