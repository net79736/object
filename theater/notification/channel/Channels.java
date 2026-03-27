package notification.channel;

import notification.core.NotificationChannel;

/**
 * 채널 생성을 담당하는 팩토리 클래스
 * "new EmailChannel()" 대신 "Channels.email()"로 직관적인 호출이 가능하게 합니다.
 */
public class Channels {
    public static NotificationChannel email() { return new EmailChannel(); }
    public static NotificationChannel sms() { return new SmsChannel(); }
    public static NotificationChannel push() { return new PushChannel(); }
    public static NotificationChannel kakao() { return new KakaoTalkChannel(); }
}