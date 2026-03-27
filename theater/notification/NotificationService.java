package notification;

import notification.core.Notification;
import notification.domain.User;

/**
 * 알림 서비스 클래스
 * 
 * 알림 전송을 담당하는 서비스 클래스입니다.
 * SRP 원칙에 따라 알림 전송 책임을 User 엔티티에서 분리하여 관리합니다.
 * DIP 원칙에 따라 구체적인 Notification 구현체가 아닌 인터페이스에 의존합니다.
 * 
 * 합성 패턴의 장점:
 * - 다양한 알림 타입을 동일한 방식으로 처리
 * - 새로운 알림 타입 추가 시 이 클래스 수정 불필요
 */
public class NotificationService {
    
    /**
     * 사용자에게 알림을 전송합니다.
     * 
     * @param notification 전송할 알림 (중요, 마케팅, 긴급 등)
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     */
    public void sendNotification(Notification notification, User user, String message) {
        notification.send(user, message);
    }
}
