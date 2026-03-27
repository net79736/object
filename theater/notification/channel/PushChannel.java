package notification.channel;

import notification.core.NotificationChannel;
import notification.domain.User;

/**
 * 푸시 알림 채널
 * 
 * 푸시 알림 전송을 담당하는 독립적인 채널 객체입니다.
 * 합성 패턴을 통해 다른 채널과 조합하여 사용할 수 있습니다.
 */
public class PushChannel implements NotificationChannel {
    private static final String CHANNEL_NAME = "PUSH";
    
    @Override
    public void send(User user, String message) {
        String deviceToken = user.getDeviceToken();
        // 실제 구현에서는 푸시 알림 API 호출
        System.out.println("🔔 Push to " + deviceToken + ": " + message);
    }
    
    @Override
    public String getChannelName() {
        return CHANNEL_NAME;
    }
}

