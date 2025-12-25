package apec.payment_order_point.main;

import java.util.List;
import java.util.Optional;
import apec.payment_order_point.common.User;
import apec.payment_order_point.main.PaymentPlan.PaymentItem;
import apec.payment_order_point.order.Order;
import apec.payment_order_point.payment.discount.PaymentDiscountPolicyRepository;
import apec.payment_order_point.payment.discount.intf.PaymentDiscountPolicy;
import apec.payment_order_point.payment.intf.PaymentHandler;

/**
 * 결제 처리를 담당하는 서비스 클래스
 * 
 * DIP 원칙에 따라 PaymentService는 구체적인 결제 처리 로직을 알 필요 없이,
 * PaymentHandler 인터페이스를 통해 결제를 처리합니다.
 * 각 결제 수단별 핸들러는 PaymentHandler 인터페이스를 구현하여 자신의 결제 로직을 처리합니다.
 * 
 * 할인 정책은 PaymentDiscountPolicyRepository를 통해 관리되며,
 * 이벤트별로 다른 할인 정책을 적용할 수 있습니다.
 */
public class PaymentService {
    private final List<PaymentHandler> paymentHandlers;
    private final PaymentDiscountPolicyRepository discountPolicyRepository;

    public PaymentService(List<PaymentHandler> paymentHandlers, PaymentDiscountPolicyRepository discountPolicyRepository) {
        this.paymentHandlers = paymentHandlers;
        this.discountPolicyRepository = discountPolicyRepository;
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
        // 결제 계획에 포함된 각 결제 항목을 순차적으로 처리합니다. (각 결제 항목은 하나의 결제 수단을 나타냄. [포인트, 카드, 계좌이체 등])
        for (PaymentItem item : paymentPlan.getPaymentItems()) {
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
     * 결제 수단별 할인 정책을 적용한 최종 결제 금액을 계산합니다.
     * 
     * PaymentDiscountPolicyRepository에서 해당 결제 수단의 할인 정책을 조회하여 적용합니다.
     * 할인 정책이 없으면 원래 금액을 반환합니다.
     * 
     * @param originalAmount 원래 금액
     * @param paymentType 결제 수단
     * @return 할인 적용 후 최종 결제 금액
     */
    public int calculateFinalAmount(int originalAmount, PaymentType paymentType) {
        // 결재 타입별 할인 정책 조회
        Optional<PaymentDiscountPolicy> policy = discountPolicyRepository.findByPaymentType(paymentType);
        
        if (policy.isPresent()) {
            // 할인 정책이 있으면 할인 적용 후 금액 반환
            return policy.get().applyDiscount(originalAmount);
        }
        
        // 할인 정책이 없으면 원래 금액 반환
        return originalAmount;
    }
    
    /**
     * 결제 수단별 할인 금액을 계산합니다.
     * 
     * @param originalAmount 원래 금액
     * @param paymentType 결제 수단
     * @return 할인 금액
     */
    public int calculateDiscountAmount(int originalAmount, PaymentType paymentType) {
        Optional<PaymentDiscountPolicy> policy = discountPolicyRepository.findByPaymentType(paymentType);
        
        if (policy.isPresent()) {
            return policy.get().calculateDiscountAmount(originalAmount);
        }
        
        // 할인 정책이 없으면 할인 금액 0 반환
        return 0;
    }
}

