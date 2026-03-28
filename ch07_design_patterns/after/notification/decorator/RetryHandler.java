package ch07_design_patterns.after.notification.decorator;

import ch07_design_patterns.after.notification.core.NotificationChannel;import ch07_design_patterns.after.notification.domain.User;
/**
 * 재시도 핸들러
 * 
 * 데코레이터 패턴을 사용하여 채널에 재시도 기능을 추가합니다.
 * 전송 실패 시 지정된 횟수만큼 재시도합니다.
 * 
 * 합성 패턴의 장점:
 * - 재시도 로직을 독립적으로 관리
 * - 모든 채널에 동일한 재시도 로직 적용 가능
 * - 재시도 정책 변경 시 한 곳만 수정
 */
public class RetryHandler implements NotificationChannel {
    private final NotificationChannel channel;
    private final int maxRetries;
    
    public RetryHandler(NotificationChannel channel, int maxRetries) {
        this.channel = channel;
        this.maxRetries = maxRetries;
    }
    
    @Override
    public void deliver(User user, String message) {
        int attempt = 0;
        Exception lastException = null;
        
        while (attempt < maxRetries) {
            try {
                channel.deliver(user, message);
                return; // 성공
            } catch (Exception e) {
                lastException = e;
                attempt++;
                
                if (attempt < maxRetries) {
                    System.out.println("재시도 " + attempt + "/" + maxRetries + ": " + channel.getChannelName());
                }
            }
        }
        
        // 모든 재시도 실패
        throw new RuntimeException("전송 실패: " + channel.getChannelName() + " (재시도 " + maxRetries + "회)", lastException);
    }
    
    @Override
    public String getChannelName() {
        return channel.getChannelName();
    }
}

