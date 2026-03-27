package notification.core;

import java.util.List;

import notification.domain.NotificationSettings;
import notification.domain.User;

public class DefaultNotification implements Notification {
    private final List<NotificationChannel> channels;

    public DefaultNotification(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    @Override
    public void send(User user, String message) {
        NotificationSettings settings = user.getNotificationSettings();
        
        // 채널들을 순회하며 사용자 설정에 따라 필터링 후 전송
        channels.stream()
            .filter(c -> settings.isChannelEnabled(c.getChannelName()))
            .forEach(c -> c.send(user, message));
    }
}
