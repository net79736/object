package apec.payment_order_point.order;

import static apec.payment_order_point.report.ReportGenerator.buildPaymentInfo;
import apec.payment_order_point.main.PaymentService;
import apec.payment_order_point.notification.NotificationService;

/**
 * 주문 처리를 담당하는 서비스 클래스
 * 
 * 주문 처리 시 재고 확인/차감, 할인 적용, 결제 처리, 알림 전송을 수행합니다.
 */
public class OrderService {
    private final PaymentService paymentService; // 결제 서비스
    private final NotificationService notificationService; // 알림 서비스

    public OrderService(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    /**
     * 주문을 처리합니다.
     * 1. 재고 확인 및 차감
     * 2. 주문 총액 계산
     * 3. 사용자 할인 적용 (등급 할인 + 쿠폰 할인)
     * 4. 결제 수단별 할인 적용
     * 5. 결제 처리
     * 6. 주문 완료 알림 전송
     * 
     * @param order 처리할 주문
     */
    public void processOrder(Order order) {
        // 1. 재고 확인 및 차감
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.decreaseStock(item.getQuantity());
        }

        // 2. 주문 총액 계산
        int totalAmount = calculateTotalAmount(order);

        // 3. 사용자 할인 적용 (등급 할인 + 쿠폰 할인)
        // "묻지 말고 시켜라" 원칙: User가 자신의 할인을 계산합니다.
        int afterUserDiscount = order.getFinalTotalAmount(totalAmount);

        // 4. 결제 수단별 할인 적용
        // 포인트 부분 결제인 경우, 나머지 금액에 대한 결제 수단에만 할인 적용
        int finalAmount = applyPaymentTypeDiscount(order, afterUserDiscount);

        // 5. 결제 처리
        // 포인트 부분 결제를 지원합니다 (포인트 + 다른 결제 수단)
        paymentService.processPayment(order, finalAmount);

        // 6. 주문 완료 알림 전송
        String paymentInfo = buildPaymentInfo(order, finalAmount);
        String message = String.format("주문이 완료되었습니다. %s", paymentInfo);
        order.sendOrderCompletedNotification(message);
    }
    
    /**
     * 결제 수단별 할인을 적용합니다.
     * 
     * 포인트 부분 결제인 경우:
     * - 포인트는 할인 없음 (일반적으로 포인트는 할인이 없음)
     * - 나머지 금액에 대해서만 결제 수단별 할인 적용
     * 
     * 단일 결제인 경우:
     * - 해당 결제 수단의 할인 정책 적용
     * 
     * @param order 주문
     * @param amount 할인 적용 전 금액
     * @return 결제 수단별 할인 적용 후 금액
     */
    private int applyPaymentTypeDiscount(Order order, int amount) {
        if (order.hasPointPayment()) {
            // 포인트 부분 결제인 경우
            int pointAmount = order.getPointAmount();
            int remainingAmount = amount - pointAmount;
            
            // 나머지 금액에 대해서만 결제 수단별 할인 적용
            int discountedRemainingAmount = paymentService.calculateFinalAmount(
                remainingAmount, 
                order.getPaymentType()
            );
            
            // 포인트 금액 + 할인 적용된 나머지 금액
            return pointAmount + discountedRemainingAmount;
        } else {
            // 단일 결제인 경우: 해당 결제 수단의 할인 정책 적용
            return paymentService.calculateFinalAmount(amount, order.getPaymentType());
        }
    }

    /**
     * 주문의 총 금액을 계산합니다.
     * 각 주문 항목의 (상품 가격 × 수량)을 합산합니다.
     * 
     * @param order 주문
     * @return 주문 총액
     */
    private int calculateTotalAmount(Order order) {
        int total = 0;
        for (OrderItem item : order.getItems()) {
            int itemPrice = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            total += itemPrice * quantity;
        }
        return total;
    }

}
