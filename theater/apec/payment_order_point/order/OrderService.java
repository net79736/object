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
     * 4. 결제 수단별 할인 적용 및 결제 처리
     * 5. 주문 완료 알림 전송
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
        int finalTotalAmount = order.getFinalTotalAmount(totalAmount);

        // 4. 결제 수단에 따라 결제 처리
        // 포인트 부분 결제를 지원합니다 (포인트 + 다른 결제 수단)
        paymentService.processPayment(order, finalTotalAmount);

        // 5. 주문 완료 알림 전송
        String paymentInfo = buildPaymentInfo(order, finalTotalAmount);
        String message = String.format("주문이 완료되었습니다. %s", paymentInfo);
        order.sendOrderCompletedNotification(message);
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
