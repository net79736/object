package notification.notification;

import java.util.Arrays;
import java.util.List;

import notification.channel.EmailChannel;
import notification.channel.PushChannel;
import notification.channel.intf.NotificationChannel;
import notification.domain.NotificationSettings;
import notification.domain.User;
import notification.notification.intf.Notification;

/**
 * 마케팅 알림
 * 
 * Push + 이메일 채널을 동시에 사용하여 전송합니다.
 * 합성 패턴을 사용하여 두 채널을 조합합니다.
 * 
 * 사용자가 SMS를 차단해도 Push와 이메일은 정상 전송됩니다.
 */
public class MarketingNotification implements Notification {
    private final List<NotificationChannel> channels;
    
    public MarketingNotification() {
        // 합성: Push와 이메일 채널 조합
        this.channels = Arrays.asList(
            new PushChannel(),
            new EmailChannel()
        );
    }
    
    @Override
    public void send(User user, String message) {
        NotificationSettings settings = user.getNotificationSettings();
        
        // 사용자 설정에 따라 활성화된 채널만 전송
        channels.stream()
            .filter(channel -> settings.isChannelEnabled(channel.getChannelName()))
            .forEach(channel -> channel.send(user, message));
    }
}

