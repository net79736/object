package notification.notification;

import java.util.Arrays;
import java.util.List;

import notification.channel.EmailChannel;
import notification.channel.SmsChannel;
import notification.channel.intf.NotificationChannel;
import notification.domain.NotificationSettings;
import notification.domain.User;
import notification.notification.intf.Notification;

/**
 * 중요 알림
 * 
 * 이메일 + SMS 채널을 동시에 사용하여 전송합니다.
 * 합성 패턴을 사용하여 두 채널을 조합합니다.
 * 
 * 상속 구조의 문제점:
 * - EmailSmsNotification 클래스를 만들어야 함
 * - 새로운 조합이 생길 때마다 클래스 추가 필요
 * 
 * 합성 패턴의 장점:
 * - 채널 리스트로 간단하게 조합 가능
 * - 런타임에 채널 변경 가능
 */
public class ImportantNotification implements Notification {
    private final List<NotificationChannel> channels;
    
    public ImportantNotification() {
        // 합성: 여러 채널을 조합
        this.channels = Arrays.asList(
            new EmailChannel(),
            new SmsChannel()
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

