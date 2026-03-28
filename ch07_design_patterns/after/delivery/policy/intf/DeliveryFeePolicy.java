package ch07_design_patterns.after.delivery.policy.intf;

/**
 * 배송비 계산 정책 인터페이스
 * 
 * 전략 패턴을 사용하여 다양한 배송비 계산 정책을 적용할 수 있습니다.
 * 각 정책은 독립적으로 구현되며, 합성을 통해 자유롭게 조합할 수 있습니다.
 * 
 * 상속 기반 설계의 문제점:
 * - 정책을 조합하려면 새로운 클래스를 만들어야 함 (예: ExpressWeekendDelivery)
 * - 정책 적용 순서가 고정되어 변경 불가능
 * - 상속 계층이 깊어질수록 유지보수 어려움
 * 
 * 합성 기반 설계의 장점:
 * - 정책을 독립적으로 구현하여 재사용성 향상
 * - 런타임에 정책을 자유롭게 조합 가능
 * - 정책 적용 순서를 동적으로 변경 가능
 */
public interface DeliveryFeePolicy {
    /**
     * 배송비를 계산합니다.
     * 
     * @param baseFee 이전 정책이 계산한 기본 배송비 (체인에서 전달받은 값)
     * @return 정책이 적용된 배송비
     */
    int calculateFee(int baseFee);
}
/**
public class DefaultNotification implements Notification {
    private final List<NotificationChannel> channels;

    public DefaultNotification(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    @Override
    public void send(User user, String message) {
        for (NotificationChannel channel : channels) {
            channel.send(user, message);
        }
    }
}
 */
