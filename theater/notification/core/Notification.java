package notification.core;

import java.util.List;

import notification.channel.EmailChannel;
import notification.channel.PushChannel;
import notification.channel.SmsChannel;
import notification.domain.User;

/**
 * 알림 인터페이스
 * 
 * 각 알림 타입(중요, 마케팅, 긴급)은 이 인터페이스를 구현합니다.
 * 합성 패턴을 사용하여 여러 채널을 조합하여 전송합니다.
 * 
 * 상속 대신 합성을 사용함으로써:
 * - 여러 채널을 유연하게 조합 가능
 * - 런타임에 채널 변경 가능
 * - 새로운 채널 추가 시 기존 코드 수정 불필요
 */
public interface Notification {
    /**
     * 사용자에게 알림을 전송합니다.
     * 
     * 내부적으로 여러 채널을 조합하여 전송합니다.
     * 사용자의 알림 설정에 따라 차단된 채널은 제외됩니다.
     * 
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     */
    void publish(User user, String message);

    // 1. ImportantNotification.java 내용이 이리로 옵니다.
    static Notification important() {
        return new DefaultNotification(List.of(
            new EmailChannel(), 
            new SmsChannel()
        ));
    }

    // 2. MarketingNotification.java 내용이 이리로 옵니다.
    static Notification marketing() {
        return new DefaultNotification(List.of(
            new PushChannel(), 
            new EmailChannel()
        ));
    }

    // 3. UrgentNotification.java 내용이 이리로 옵니다.
    static Notification urgent() {
        return new DefaultNotification(List.of(
            new EmailChannel().withLogging(),
            new SmsChannel().withRetry(3),
            new PushChannel()
        ));
    }
}

