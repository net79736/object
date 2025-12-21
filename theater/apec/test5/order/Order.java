package apec.test5.order;

import java.util.List;
import apec.test5.common.User;
import apec.test5.payment.main.PaymentType;

/**
 * 주문 클래스
 * 
 * 주문 정보와 결제 정보를 담고 있습니다.
 * 포인트 부분 결제를 지원합니다.
 */
public class Order {
    private List<OrderItem> items;
    private User user;
    private PaymentType paymentType;
    private Integer pointAmount; // 포인트 사용 금액 (null이면 포인트 미사용)

    public Order(List<OrderItem> items, User user, PaymentType paymentType) {
        this(items, user, paymentType, null);
    }

    /**
     * 포인트 부분 결제를 포함한 주문 생성
     * 
     * @param items 주문 항목
     * @param user 사용자
     * @param paymentType 나머지 금액 결제 수단 (포인트 사용 시)
     * @param pointAmount 포인트 사용 금액 (null이면 포인트 미사용)
     */
    public Order(List<OrderItem> items, User user, PaymentType paymentType, Integer pointAmount) {
        this.items = items;
        this.user = user;
        this.paymentType = paymentType;
        this.pointAmount = pointAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Integer getPointAmount() {
        return pointAmount;
    }

    /**
     * 포인트를 사용하는지 확인합니다.
     * 
     * @return 포인트 사용 여부
     */
    public boolean hasPointPayment() {
        return pointAmount != null && pointAmount > 0;
    }

    public int getFinalTotalAmount(int totalAmount) {
        return user.calculateFinalTotal(totalAmount);
    }
}
