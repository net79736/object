package apec.test5.payment.main;

import java.util.List;
import apec.test5.common.User;
import apec.test5.order.Order;
import apec.test5.payment.intf.PaymentHandler;

/**
 * 결제 처리를 담당하는 서비스 클래스
 * 
 * DIP 원칙에 따라 PaymentService는 구체적인 결제 처리 로직을 알 필요 없이,
 * PaymentHandler 인터페이스를 통해 결제를 처리합니다.
 * 각 결제 수단별 핸들러는 PaymentHandler 인터페이스를 구현하여 자신의 결제 로직을 처리합니다.
 */
public class PaymentService {
    private final List<PaymentHandler> paymentHandlers;

    public PaymentService(List<PaymentHandler> paymentHandlers) {
        this.paymentHandlers = paymentHandlers;
    }

    /**
     * 주문에 대한 결제를 처리합니다.
     * 포인트 부분 결제를 지원합니다.
     * 
     * 포인트를 사용하는 경우:
     * 1. 포인트로 일부 금액 결제
     * 2. 나머지 금액은 다른 결제 수단으로 결제
     * 
     * 포인트를 사용하지 않는 경우:
     * - 지정된 결제 수단으로 전체 금액 결제
     * 
     * @param order 결제할 주문
     * @param totalAmount 결제할 총 금액
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 수단 오류 등)
     */
    public void processPayment(Order order, int totalAmount) {
        if (order.hasPointPayment()) {
            // 포인트 부분 결제
            processMixedPayment(order, totalAmount);
        } else {
            // 단일 결제 수단으로 전체 금액 결제
            processSinglePayment(order, totalAmount);
        }
    }

    /**
     * 포인트와 다른 결제 수단을 함께 사용하는 복합 결제를 처리합니다.
     * 
     * @param order 결제할 주문
     * @param totalAmount 결제할 총 금액
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 수단 오류 등)
     */
    private void processMixedPayment(Order order, int totalAmount) {
        User user = order.getUser();
        int pointAmount = order.getPointAmount();
        int remainingAmount = totalAmount - pointAmount;

        // 포인트 사용 금액이 총 금액보다 큰 경우 검증
        if (pointAmount > totalAmount) {
            throw new RuntimeException("포인트 사용 금액이 총 금액보다 큽니다. 포인트: " + pointAmount + ", 총액: " + totalAmount);
        }

        // 1. 포인트로 일부 금액 결제
        if (pointAmount > 0) {
            PaymentHandler pointHandler = findHandler(PaymentType.POINT);
            pointHandler.processPayment(user, pointAmount);
        }

        // 2. 나머지 금액을 다른 결제 수단으로 결제
        if (remainingAmount > 0) {
            PaymentType paymentType = order.getPaymentType();
            // 포인트 결제 수단이면 안됨
            if (paymentType == PaymentType.POINT) {
                throw new RuntimeException("포인트 부분 결제 시 나머지 금액은 다른 결제 수단을 사용해야 합니다.");
            }
            PaymentHandler handler = findHandler(paymentType);
            handler.processPayment(user, remainingAmount);
        }
    }

    /**
     * 단일 결제 수단으로 결제를 처리합니다.
     * 
     * @param order 결제할 주문
     * @param totalAmount 결제할 총 금액
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 수단 오류 등)
     */
    private void processSinglePayment(Order order, int totalAmount) {
        PaymentType paymentType = order.getPaymentType();
        User user = order.getUser();

        PaymentHandler handler = findHandler(paymentType);
        handler.processPayment(user, totalAmount);
    }

    /**
     * 결제 수단에 맞는 PaymentHandler를 찾습니다.
     * 
     * @param paymentType 결제 수단 타입
     * @return 해당 결제 수단을 처리하는 PaymentHandler
     * @throws RuntimeException 지원하지 않는 결제 수단인 경우
     */
    private PaymentHandler findHandler(PaymentType paymentType) {
        return paymentHandlers.stream()
                .filter(handler -> handler.getPaymentType() == paymentType)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("지원하지 않는 결제 수단입니다: " + paymentType));
    }

    /**
     * 결제 수단별 할인율을 적용한 최종 결제 금액을 계산합니다.
     * 
     * @param originalAmount 원래 금액
     * @param paymentType 결제 수단
     * @return 할인 적용 후 최종 결제 금액
     */
    public int calculateFinalAmount(int originalAmount, PaymentType paymentType) {
        PaymentHandler handler = findHandler(paymentType);
        int discountRate = handler.getDiscountRate();
        int discountAmount = (originalAmount * discountRate) / 100;
        return originalAmount - discountAmount;
    }
}

