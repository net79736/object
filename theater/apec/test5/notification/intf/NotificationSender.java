package apec.test5.notification.intf;

import apec.test5.common.User;
import apec.test5.notification.NotificationSettings;

/**
 * 알림 전송을 담당하는 인터페이스
 * 
 * DIP 원칙에 따라 각 구현체가 User에서 자신에게 필요한 정보를 추출합니다.
 * 각 구현체는 자신의 활성화 조건을 판단합니다.
 */
public interface NotificationSender {
    /**
     * User 객체에서 필요한 정보를 추출하여 알림을 전송합니다.
     * DIP 원칙에 따라 각 구현체가 User에서 자신에게 필요한 정보를 추출합니다.
     * 
     * @param user 알림을 받을 사용자
     * @param message 전송할 메시지
     */
    void send(User user, String message);
    
    /**
     * NotificationSettings에 따라 이 sender가 활성화되어 있는지 확인합니다.
     * DIP 원칙에 따라 각 구현체가 자신의 활성화 조건을 판단합니다.
     * 
     * @param settings 알림 설정
     * @return 활성화되어 있으면 true, 아니면 false
     */
    boolean isEnabled(NotificationSettings settings);
}

