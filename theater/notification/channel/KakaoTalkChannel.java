package notification.channel;

import notification.channel.intf.NotificationChannel;
import notification.domain.User;

/**
 * 카카오톡 알림 채널
 * 
 * 확장성 검증을 위한 예시 채널입니다.
 * 합성 패턴의 장점을 보여주기 위해 추가되었습니다.
 * 
 * 합성 패턴의 장점:
 * - 이 클래스만 추가하면 됨 (기존 코드 수정 불필요)
 * - 기존 EmailChannel, SmsChannel, PushChannel과 동일한 구조
 * - 부가 기능(재시도, 로깅, 타임아웃)도 그대로 사용 가능
 */
public class KakaoTalkChannel implements NotificationChannel {
    private static final String CHANNEL_NAME = "KAKAOTALK";
    
    @Override
    public void send(User user, String message) {
        String userId = user.getEmail(); // 실제로는 카카오톡 사용자 ID 사용
        // 실제 구현에서는 카카오톡 알림톡 API 호출
        System.out.println("💬 KakaoTalk to " + userId + ": " + message);
    }
    
    @Override
    public String getChannelName() {
        return CHANNEL_NAME;
    }
}

