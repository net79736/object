package notification.feature.logging;

import notification.channel.intf.NotificationChannel;
import notification.domain.User;

/**
 * 로깅 핸들러
 * 
 * 데코레이터 패턴을 사용하여 채널에 로깅 기능을 추가합니다.
 * 전송 전후로 로그를 기록합니다.
 * 
 * 합성 패턴의 장점:
 * - 로깅 로직을 독립적으로 관리
 * - 필요할 때만 채널에 래핑하여 사용
 * - 로깅 정책 변경 시 한 곳만 수정
 */
public class LoggingHandler implements NotificationChannel {
    private final NotificationChannel channel;
    
    public LoggingHandler(NotificationChannel channel) {
        this.channel = channel;
    }
    
    @Override
    public void send(User user, String message) {
        String channelName = channel.getChannelName();
        long startTime = System.currentTimeMillis();
        
        try {
            System.out.println("[LOG] 알림 전송 시작 - 채널: " + channelName + ", 사용자: " + user.getEmail());
            
            channel.send(user, message);
            
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("[LOG] 알림 전송 완료 - 채널: " + channelName + ", 소요시간: " + duration + "ms");
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("[LOG] 알림 전송 실패 - 채널: " + channelName + ", 소요시간: " + duration + "ms, 에러: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public String getChannelName() {
        return channel.getChannelName();
    }
}

