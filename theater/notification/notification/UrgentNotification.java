package notification.notification;

import java.util.Arrays;
import java.util.List;

import notification.channel.EmailChannel;
import notification.channel.PushChannel;
import notification.channel.SmsChannel;
import notification.channel.intf.NotificationChannel;
import notification.domain.NotificationSettings;
import notification.domain.User;
import notification.notification.intf.Notification;

/**
 * 긴급 알림
 * 
 * 모든 채널(SMS + Push + 이메일)을 동시에 사용하여 전송합니다.
 * 합성 패턴을 사용하여 세 채널을 조합합니다.
 * 
 * 상속 구조였다면:
 * - EmailSmsPushNotification 클래스 필요
 * - 새로운 채널 추가 시 모든 조합 클래스 수정 필요
 * 
 * 합성 패턴의 장점:
 * - 채널 리스트에 추가만 하면 됨
 * - 기존 코드 수정 불필요
 */
public class UrgentNotification implements Notification {
    private final List<NotificationChannel> channels;
    
    public UrgentNotification() {
        // 합성: 모든 채널 조합
        this.channels = Arrays.asList(
            new EmailChannel(),
            new SmsChannel(),
            new PushChannel()
        );
    }
    
    @Override
    public void send(User user, String message) {
        NotificationSettings settings = user.getNotificationSettings();
        
        // 사용자 설정에 따라 활성화된 채널만 전송
        // 긴급 알림이지만 사용자가 차단한 채널은 제외
        channels.stream()
            .filter(channel -> settings.isChannelEnabled(channel.getChannelName()))
            .forEach(channel -> channel.send(user, message));
    }
}

