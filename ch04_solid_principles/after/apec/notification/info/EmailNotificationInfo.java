package ch04_solid_principles.after.apec.notification.info;

import ch04_solid_principles.after.apec.notification.User;import ch04_solid_principles.after.apec.notification.info.enums.NotificationTypeEnum;import ch04_solid_principles.after.apec.notification.info.intf.NotificationInfo;
/**
 * 이메일 알림 정보 어댑터
 * User 객체에서 이메일 주소를 추출하는 어댑터입니다.
 */
public class EmailNotificationInfo implements NotificationInfo {
    @Override
    public NotificationTypeEnum getNotificationType() {
        return NotificationTypeEnum.EMAIL;
    }

    @Override
    public String extractInfo(User user) {
        return user.getEmail();
    }
}
