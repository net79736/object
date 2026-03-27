package notification.core;

import java.util.List;

import notification.domain.NotificationSettings;
import notification.domain.User;

/**
 * 기본 알림 클래스
 * 
 * 활성화된 알림 채널을 사용하여 알림을 전송합니다.
 */
public class DefaultNotification implements Notification {
    /**
     * 활성화된 알림 채널 목록
     */
    private final List<NotificationChannel> channels; // 하드웨어 장비

    public DefaultNotification(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    @Override
    public void publish(User user, String message) {
        NotificationSettings settings = user.getNotificationSettings();
        
        // 채널들을 순회하며 사용자 설정에 따라 필터링 후 전송
        channels.stream()
            .filter(c -> settings.isChannelEnabled(c.getChannelName()))
            .forEach(c -> c.deliver(user, message));
    }
}