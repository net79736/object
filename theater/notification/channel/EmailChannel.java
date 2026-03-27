package notification.channel;

import notification.core.NotificationChannel;
import notification.domain.User;

/**
 * 이메일 알림 채널
 * 
 * 이메일 전송을 담당하는 독립적인 채널 객체입니다.
 * 합성 패턴을 통해 다른 채널과 조합하여 사용할 수 있습니다.
 */
public class EmailChannel implements NotificationChannel {
    private static final String CHANNEL_NAME = "EMAIL";
    
    @Override
    public void deliver(User user, String message) {
        String email = user.getEmail();
        // 실제 구현에서는 이메일 전송 API 호출
        System.out.println("📧 Email to " + email + ": " + message);
    }
    
    @Override
    public String getChannelName() {
        return CHANNEL_NAME;
    }
}