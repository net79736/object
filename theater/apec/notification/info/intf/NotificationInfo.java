package apec.notification.info.intf;

import apec.notification.User;
import apec.notification.info.enums.NotificationTypeEnum;

/**
 * 알림 정보 어댑터 인터페이스
 * User의 기본 정보를 알림 전송에 필요한 형태로 변환하는 어댑터 역할을 합니다.
 * 
 * 어댑터 패턴을 사용하여:
 * - User 도메인 엔티티는 자신의 기본 정보를 직접 보유
 * - NotificationInfo는 User를 받아서 알림 전송에 필요한 정보를 추출
 * - 타입 안전성 확보 및 다운캐스팅 제거
 */
public interface NotificationInfo {
    /**
     * 알림 타입을 반환합니다.
     * @return 알림 타입 (EMAIL, SMS, PUSH)
     */
    NotificationTypeEnum getNotificationType();
    
    /**
     * User 객체에서 알림 전송에 필요한 정보를 추출합니다.
     * 각 구현체는 자신의 알림 타입에 맞는 정보를 User에서 추출합니다.
     * 
     * @param user 알림을 받을 사용자
     * @return 알림 전송에 필요한 정보 (이메일 주소, 전화번호, 디바이스 토큰 등)
     */
    String extractInfo(User user);
}
