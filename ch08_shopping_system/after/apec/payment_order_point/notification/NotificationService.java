package ch08_shopping_system.after.apec.payment_order_point.notification;

import ch08_shopping_system.after.apec.payment_order_point.common.User;
/**
 * 알림 서비스 클래스
 * 
 * 주문 완료 등의 이벤트 발생 시 사용자에게 알림을 전송합니다.
 * DIP 원칙에 따라 NotificationService는 구체적인 sender 타입을 알 필요가 없습니다.
 */
public class NotificationService {
    /**
     * 사용자에게 알림을 전송합니다.
     * 
     * User 객체가 자신의 설정에 따라 알림을 전송합니다.
     * DIP 원칙에 따라 NotificationService는 구체적인 sender 타입을 알 필요가 없습니다.
     * 
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     */
    public void sendNotification(User user, String message) {
        // User 객체가 자신의 설정에 따라 알림을 전송합니다.
        // DIP 원칙에 따라 NotificationService는 구체적인 sender 타입을 알 필요가 없습니다.
        user.sendNotification(message);
    }
}

