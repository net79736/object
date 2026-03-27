package notification.enums;

/**
 * 알림 타입 열거형
 * 
 * 각 알림 타입은 특정 채널 조합을 사용합니다:
 * - IMPORTANT: 이메일 + SMS
 * - MARKETING: Push + 이메일
 * - URGENT: SMS + Push + 이메일 (모든 채널)
 */
public enum NotificationTypeEnum {
    IMPORTANT,  // 중요 알림 (이메일 + SMS)
    MARKETING,  // 마케팅 알림 (Push + 이메일)
    URGENT      // 긴급 알림 (모든 채널)
}