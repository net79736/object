package ch04_solid_principles.after.apec.notification;

import java.util.List;

import ch04_solid_principles.after.apec.notification.sender.intf.NotificationSender;
/**
 * 알림 전송을 담당하는 서비스 클래스
 * SRP 원칙에 따라 알림 전송 책임을 User 엔티티에서 분리하여 관리합니다.
 * DIP 원칙에 따라 구체적인 sender 타입이 아닌 NotificationSender 인터페이스에 의존합니다.
 * 
 * 각 sender는 자신의 타입에 맞는 NotificationInfo 어댑터를 사용하여
 * User 객체에서 필요한 정보를 타입 안전하게 추출합니다.
 */
public class NotificationService {
    private List<NotificationSender> notificationSenders; // 알림 전송자 목록

    public NotificationService(List<NotificationSender> notificationSenders) {
        this.notificationSenders = notificationSenders;
    }

    /**
     * 사용자의 알림 설정에 따라 활성화된 sender만을 사용하여 알림을 전송합니다.
     * DIP 원칙에 따라 NotificationService는 구체적인 sender 타입을 알 필요 없이,
     * NotificationSender 인터페이스의 isEnabled 메서드를 통해 필터링합니다.
     * 
     * 각 sender는 자신의 타입에 맞는 NotificationInfo 어댑터를 사용하여
     * User 객체에서 필요한 정보(이메일, 전화번호, 디바이스 토큰)를 타입 안전하게 추출합니다.
     * 
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     */
    public void sendNotification(User user, String message) {
        NotificationSettings settings = user.getNotificationSettings(); // 알림 설정 조회
        
        // 활성화된 sender만을 사용하여 알림을 전송합니다.
        notificationSenders.stream()
                .filter(sender -> sender.isEnabled(settings))
                .forEach(sender -> sender.send(user, message));
    }
}
