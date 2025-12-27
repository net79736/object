package notification;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import notification.channel.EmailChannel;
import notification.channel.PushChannel;
import notification.channel.SmsChannel;
import notification.channel.intf.NotificationChannel;
import notification.domain.NotificationSettings;
import notification.domain.User;
import notification.feature.logging.LoggingHandler;
import notification.feature.retry.RetryHandler;
import notification.feature.timeout.TimeoutHandler;
import notification.notification.ImportantNotification;
import notification.notification.MarketingNotification;
import notification.notification.intf.Notification;

/**
 * 알림 시스템 테스트 클래스
 * 
 * 문제에서 요구한 세 가지 시나리오를 테스트합니다:
 * 1. 중요한 주문 완료 알림 (이메일 + SMS)
 * 2. 마케팅 쿠폰 알림 (Push + 이메일, SMS는 차단됨)
 * 3. 긴급 시스템 점검 알림 (모든 채널, 재시도 3회)
 */
public class NotificationTest {
    
    public static void main(String[] args) {
        NotificationTest test = new NotificationTest();
        
        System.out.println("=== 알림 시스템 리팩토링 테스트 ===\n");
        
        test.scenario1_ImportantOrderNotification();
        test.scenario2_MarketingCouponNotification();
        test.scenario3_UrgentSystemMaintenance();
        test.scenario5_DecoratorPatternExample();
        test.scenario4_ExtensibilityTest();
    }
    
    /**
     * 시나리오 1: 중요한 주문 완료 알림 (이메일 + SMS)
     */
    private void scenario1_ImportantOrderNotification() {
        System.out.println("--- 시나리오 1: 중요한 주문 완료 알림 (이메일 + SMS) ---");
        
        // 사용자 생성 (모든 채널 활성화)
        User user = createUser("user1@example.com", "010-1234-5678", "device-token-123", new NotificationSettings());
        
        // 중요 알림 생성 (이메일 + SMS 조합)
        Notification notification = new ImportantNotification();
        
        // 알림 전송
        NotificationService service = new NotificationService();
        service.sendNotification(notification, user, "주문이 완료되었습니다. 주문번호: ORD-2024-001");
        
        System.out.println();
    }
    
    /**
     * 시나리오 2: 마케팅 쿠폰 알림 (Push + 이메일, SMS는 차단됨)
     */
    private void scenario2_MarketingCouponNotification() {
        System.out.println("--- 시나리오 2: 마케팅 쿠폰 알림 (Push + 이메일, SMS는 차단됨) ---");
        
        // 사용자 생성 (SMS 차단)
        Set<String> blockedChannels = new HashSet<>();
        blockedChannels.add("SMS");
        NotificationSettings settings = new NotificationSettings(blockedChannels);
        User user = createUser("user2@example.com", "010-9876-5432", "device-token-456", settings);
        
        // 마케팅 알림 생성 (Push + 이메일 조합)
        Notification notification = new MarketingNotification();
        
        // 알림 전송 (SMS는 차단되어 Push와 이메일만 전송됨)
        NotificationService service = new NotificationService();
        service.sendNotification(notification, user, "특별 할인 쿠폰이 발급되었습니다! 30% 할인 쿠폰을 확인하세요.");
        
        System.out.println();
    }
    
    /**
     * 시나리오 3: 긴급 시스템 점검 알림 (모든 채널, 재시도 3회)
     * 
     * 데코레이터 패턴을 사용하여 각 채널에 재시도 기능을 추가합니다.
     */
    private void scenario3_UrgentSystemMaintenance() {
        System.out.println("--- 시나리오 3: 긴급 시스템 점검 알림 (모든 채널, 재시도 3회) ---");
        
        // 사용자 생성 (모든 채널 활성화)
        User user = createUser("admin@example.com", "010-1111-2222", "device-token-789", new NotificationSettings());
        
        // 데코레이터 패턴을 사용하여 각 채널에 재시도 기능 추가
        // 재시도 3회가 적용된 채널들 생성
        NotificationChannel emailWithRetry = new RetryHandler(new EmailChannel(), 3);
        NotificationChannel smsWithRetry = new RetryHandler(new SmsChannel(), 3);
        NotificationChannel pushWithRetry = new RetryHandler(new PushChannel(), 3);
        
        // 재시도가 적용된 채널들로 긴급 알림 생성
        Notification notification = createUrgentNotificationWithRetry(
            emailWithRetry, 
            smsWithRetry, 
            pushWithRetry
        );
        
        // 알림 전송
        NotificationService service = new NotificationService();
        service.sendNotification(notification, user, "긴급: 시스템 점검 예정 (2024-01-15 02:00 ~ 04:00)");
        
        System.out.println();
    }
    
    /**
     * 재시도 기능이 적용된 긴급 알림을 생성합니다.
     * 
     * @param emailChannel 이메일 채널 (재시도 적용됨)
     * @param smsChannel SMS 채널 (재시도 적용됨)
     * @param pushChannel Push 채널 (재시도 적용됨)
     * @return 재시도 기능이 적용된 긴급 알림
     */
    private Notification createUrgentNotificationWithRetry(
            NotificationChannel emailChannel,
            NotificationChannel smsChannel,
            NotificationChannel pushChannel) {
        return new Notification() {
            private final List<NotificationChannel> channels = Arrays.asList(
                emailChannel,
                smsChannel,
                pushChannel
            );
            
            @Override
            public void send(User user, String message) {
                NotificationSettings settings = user.getNotificationSettings();
                channels.stream()
                    .filter(channel -> settings.isChannelEnabled(channel.getChannelName()))
                    .forEach(channel -> channel.send(user, message));
            }
        };
    }
    
    /**
     * 시나리오 5: 데코레이터 패턴 예시 (재시도, 로깅, 타임아웃)
     * 
     * 부가 기능을 독립적으로 추가하는 방법을 실제로 보여줍니다.
     */
    private void scenario5_DecoratorPatternExample() {
        System.out.println("--- 시나리오 5: 데코레이터 패턴 예시 (재시도, 로깅, 타임아웃) ---");
        
        // 사용자 생성
        User user = createUser("test@example.com", "010-9999-8888", "device-token-test", new NotificationSettings());
        
        System.out.println("데코레이터 패턴을 사용하여 채널에 부가 기능을 추가합니다:");
        System.out.println("1. 기본 채널 생성");
        System.out.println("2. 재시도 핸들러로 래핑 (3회 재시도)");
        System.out.println("3. 로깅 핸들러로 래핑");
        System.out.println("4. 타임아웃 핸들러로 래핑 (5초 타임아웃)");
        System.out.println();
        
        // 데코레이터 패턴 적용: 기본 채널 → 재시도 → 로깅 → 타임아웃
        NotificationChannel emailChannel = new EmailChannel();
        NotificationChannel retryChannel = new RetryHandler(emailChannel, 3);
        NotificationChannel loggingChannel = new LoggingHandler(retryChannel);
        NotificationChannel timeoutChannel = new TimeoutHandler(loggingChannel, 5000);
        
        System.out.println("실제 전송 테스트:");
        System.out.println("전송 순서: 타임아웃 체크 → 로깅 → 재시도 → 이메일 전송");
        System.out.println();
        
        // 실제로 전송해보기
        try {
            timeoutChannel.send(user, "데코레이터 패턴 테스트 메시지");
        } catch (Exception e) {
            System.out.println("전송 중 오류 발생: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * 시나리오 4: 확장성 검증 - 카카오톡 채널 추가
     * 
     * 합성 패턴의 장점을 보여주는 테스트입니다.
     * 새로운 채널을 추가해도 기존 코드 수정이 최소화됩니다.
     */
    private void scenario4_ExtensibilityTest() {
        System.out.println("--- 시나리오 4: 확장성 검증 - 카카오톡 채널 추가 ---");
        
        System.out.println("합성 패턴의 장점:");
        System.out.println("1. KakaoTalkChannel 클래스만 추가하면 됨");
        System.out.println("2. 기존 EmailChannel, SmsChannel, PushChannel 코드 수정 불필요");
        System.out.println("3. 새로운 알림 타입에 KakaoTalkChannel을 리스트에 추가만 하면 됨");
        System.out.println("4. 부가 기능(재시도, 로깅, 타임아웃)도 기존 핸들러 그대로 사용 가능");
        
        System.out.println("\n예시 코드:");
        System.out.println("// 1. KakaoTalkChannel 클래스 추가 (기존 채널과 동일한 구조)");
        System.out.println("public class KakaoTalkChannel implements NotificationChannel { ... }");
        System.out.println("");
        System.out.println("// 2. 알림 타입에 추가 (기존 코드 수정 최소화)");
        System.out.println("this.channels = Arrays.asList(");
        System.out.println("    new EmailChannel(),");
        System.out.println("    new SmsChannel(),");
        System.out.println("    new PushChannel(),");
        System.out.println("    new KakaoTalkChannel()  // 추가만 하면 됨");
        System.out.println(");");
        
        System.out.println();
    }
    
    /**
     * 테스트용 사용자 생성 헬퍼 메서드
     */
    private User createUser(String email, String phone, String deviceToken, NotificationSettings settings) {
        return new User(email, phone, deviceToken, settings);
    }
}

