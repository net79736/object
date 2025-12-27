package notification.channel.intf;

import notification.domain.User;

/**
 * 알림 채널 인터페이스
 * 
 * 각 전송 채널(이메일, SMS, Push 등)은 이 인터페이스를 구현합니다.
 * 합성 패턴의 핵심으로, 각 채널을 독립적인 객체로 만들어 조합 가능하게 합니다.
 * 
 * DIP 원칙에 따라 구체적인 채널 구현체가 아닌 이 인터페이스에 의존합니다.
 * Strategy 패턴을 사용하여 다양한 채널 전송 방식을 지원합니다.
 */
public interface NotificationChannel {
    /**
     * 사용자에게 알림을 전송합니다.
     * 
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     * @throws RuntimeException 전송 실패 시
     */
    void send(User user, String message);
    
    /**
     * 채널 이름을 반환합니다.
     * NotificationSettings에서 채널 차단 여부를 확인할 때 사용됩니다.
     * 
     * @return 채널 이름 (예: "EMAIL", "SMS", "PUSH")
     */
    String getChannelName();
}

