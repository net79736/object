package apec.notification;

public class NotificationService {
    public void sendNotification(User user, String message) {
        // User 객체가 자신의 설정에 따라 알림을 전송합니다.
        // DIP 원칙에 따라 NotificationService는 구체적인 sender 타입을 알 필요가 없습니다.
        user.sendNotification(message);
    }
}
