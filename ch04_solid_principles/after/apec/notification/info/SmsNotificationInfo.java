package ch04_solid_principles.after.apec.notification.info;

import ch04_solid_principles.after.apec.notification.User;import ch04_solid_principles.after.apec.notification.info.enums.NotificationTypeEnum;import ch04_solid_principles.after.apec.notification.info.intf.NotificationInfo;
/**
 * SMS 알림 정보 어댑터
 * User 객체에서 전화번호를 추출하는 어댑터입니다.
 */
public class SmsNotificationInfo implements NotificationInfo {
    @Override
    public NotificationTypeEnum getNotificationType() {
        return NotificationTypeEnum.SMS;
    }

    @Override
    public String extractInfo(User user) {
        return user.getPhone();
    }
}
