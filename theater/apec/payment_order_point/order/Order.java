package apec.test5.order;

import java.util.List;

import apec.test5.common.User;
import apec.test5.main.PaymentPlan;
import apec.test5.main.PaymentType;
import apec.test5.order.validator.PaymentTypeValidator;
import apec.test5.order.validator.PointAmountValidator;
import apec.test5.order.validator.PointBalanceValidator;
import apec.test5.order.validator.intf.PaymentPlanValidator;

/**
 * 주문 클래스
 * 
 * 주문 정보와 결제 정보를 담고 있습니다.
 * 포인트 부분 결제를 지원합니다.
 */
public class Order {
    private List<OrderItem> items; // 주문 항목
    private User user; // 사용자
    private PaymentType paymentType; // 결제 수단
    private Integer pointAmount; // 포인트 사용 금액 (null이면 포인트 미사용)
    private List<PaymentPlanValidator> validators; // 결제 계획 검증자 목록

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
        this(items, user, paymentType, pointAmount, null);
    }

    /**
     * 포인트 부분 결제를 포함한 주문 생성 (검증자 목록 포함)
     * 
     * @param items 주문 항목
     * @param user 사용자
     * @param paymentType 나머지 금액 결제 수단 (포인트 사용 시)
     * @param pointAmount 포인트 사용 금액 (null이면 포인트 미사용)
     * @param validators 결제 계획 검증자 목록 (null이면 기본 검증자 사용)
     */
    public Order(List<OrderItem> items, User user, PaymentType paymentType, Integer pointAmount, List<PaymentPlanValidator> validators) {
        this.items = items;
        this.user = user;
        this.paymentType = paymentType;
        this.pointAmount = pointAmount;
        this.validators = validators;
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

    /**
     * 주문의 결제 계획을 생성합니다.
     * "묻지 말고 시켜라" 원칙: Order가 자신의 결제 계획을 생성합니다.
     * 
     * DIP 원칙: Order는 구체적인 검증 로직이 아닌 PaymentPlanValidator 인터페이스에 의존합니다.
     * OCP 원칙: 새로운 검증 규칙 추가 시 새로운 Validator만 추가하면 됩니다.
     * 
     * @param totalAmount 결제할 총 금액
     * @return 결제 계획
     * @throws RuntimeException 결제 계획 생성 실패 시 (포인트 금액 검증 실패 등)
     */
    public PaymentPlan createPaymentPlan(int totalAmount) {
        if (hasPointPayment()) {
            // 포인트 부분 결제
            int pointAmount = this.pointAmount;
            int remainingAmount = totalAmount - pointAmount;

            // 검증자들을 통해 검증 수행
            validatePaymentPlan(pointAmount, totalAmount);

            return PaymentPlan.mixed(pointAmount, paymentType, remainingAmount);
        } else {
            // 포인트 부분 결제가 아닌 경우
            return PaymentPlan.single(paymentType, totalAmount);
        }
    }

    /**
     * 결제 계획을 검증합니다.
     * 
     * @param pointAmount 포인트 사용 금액
     * @param totalAmount 총 결제 금액
     */
    private void validatePaymentPlan(int pointAmount, int totalAmount) {
        List<PaymentPlanValidator> validatorsToUse = getValidators();
        for (PaymentPlanValidator validator : validatorsToUse) {
            validator.validate(this.pointAmount, totalAmount, paymentType, user);
        }
    }

    /**
     * 사용할 검증자 목록을 반환합니다.
     * validators가 null이면 기본 검증자를 생성합니다.
     * 
     * @return 검증자 목록
     */
    private List<PaymentPlanValidator> getValidators() {
        if (validators != null) {
            return validators;
        }
        // 기본 검증자 목록 반환
        return List.of(
            new PointAmountValidator(),
            new PointBalanceValidator(),
            new PaymentTypeValidator()
        );
    }

    /**
     * 주문 완료 알림을 전송합니다.
     * @param message 전송할 메시지
     */
    public void sendOrderCompletedNotification(String message) {
        user.sendNotification(message);
    }
}
