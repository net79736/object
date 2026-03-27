package notification.decorator;

import notification.core.NotificationChannel;
import notification.domain.User;

/**
 * 타임아웃 핸들러
 * 
 * 데코레이터 패턴을 사용하여 채널에 타임아웃 기능을 추가합니다.
 * 지정된 시간 내에 전송이 완료되지 않으면 예외를 발생시킵니다.
 * 
 * 합성 패턴의 장점:
 * - 타임아웃 로직을 독립적으로 관리
 * - 채널별로 다른 타임아웃 시간 설정 가능
 * - 타임아웃 정책 변경 시 한 곳만 수정
 */
public class TimeoutHandler implements NotificationChannel {
    private final NotificationChannel channel;
    private final long timeoutMillis;
    
    public TimeoutHandler(NotificationChannel channel, long timeoutMillis) {
        this.channel = channel;
        this.timeoutMillis = timeoutMillis;
    }
    
    @Override
    public void deliver(User user, String message) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 별도 스레드에서 전송 실행 (실제 구현에서는 CompletableFuture 등 사용)
            channel.deliver(user, message);
            
            long duration = System.currentTimeMillis() - startTime;
            if (duration > timeoutMillis) {
                throw new RuntimeException("타임아웃: " + channel.getChannelName() + " 전송이 " + timeoutMillis + "ms를 초과했습니다. (실제: " + duration + "ms)");
            }
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            if (duration > timeoutMillis && !(e instanceof RuntimeException && e.getMessage().contains("타임아웃"))) {
                throw new RuntimeException("타임아웃: " + channel.getChannelName() + " 전송이 " + timeoutMillis + "ms를 초과했습니다.", e);
            }
            throw e;
        }
    }
    
    @Override
    public String getChannelName() {
        return channel.getChannelName();
    }
}

