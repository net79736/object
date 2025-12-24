package apec.notification.info;

import apec.notification.User;
import apec.notification.info.enums.NotificationTypeEnum;
import apec.notification.info.intf.NotificationInfo;

/**
 * 푸시 알림 정보 어댑터
 * User 객체에서 디바이스 토큰을 추출하는 어댑터입니다.
 */
public class PushNotificationInfo implements NotificationInfo {
    @Override
    public NotificationTypeEnum getNotificationType() {
        return NotificationTypeEnum.PUSH;
    }

    @Override
    public String extractInfo(User user) {
        return user.getDeviceToken();
    }
}
