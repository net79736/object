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
     * 
     * Order가 생성한 PaymentPlan을 실행합니다.
     * "묻지 말고 시켜라" 원칙: Order가 자신의 결제 계획을 생성하고,
     * PaymentService는 단순히 계획을 실행합니다.
     * 
     * @param order 결제할 주문
     * @param totalAmount 결제할 총 금액
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 수단 오류 등)
     */
    public void processPayment(Order order, int totalAmount) {
        PaymentPlan paymentPlan = order.createPaymentPlan(totalAmount);
        executePaymentPlan(order.getUser(), paymentPlan);
    }

    /**
     * 결제 계획을 실행합니다.
     * 
     * PaymentPlan에 포함된 각 PaymentItem을 순차적으로 처리합니다.
     * 
     * @param user 결제를 수행할 사용자
     * @param paymentPlan 실행할 결제 계획
     * @throws RuntimeException 결제 실패 시 (포인트 부족, 결제 수단 오류 등)
     */
    private void executePaymentPlan(User user, PaymentPlan paymentPlan) {
        for (PaymentPlan.PaymentItem item : paymentPlan.getPaymentItems()) {
            PaymentHandler handler = findHandler(item.getPaymentType());
            handler.processPayment(user, item.getAmount());
        }
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

