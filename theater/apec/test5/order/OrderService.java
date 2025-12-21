package apec.test5.order;

import java.util.List;
import apec.test5.payment.intf.PaymentHandler;
import apec.test5.payment.main.PaymentService;

/**
 * 주문 처리를 담당하는 서비스 클래스
 * 
 * 주문 처리 시 재고 확인/차감과 결제 처리를 수행합니다.
 */
public class OrderService {
    private final PaymentService paymentService;

    public OrderService(List<PaymentHandler> paymentHandlers) {
        this.paymentService = new PaymentService(paymentHandlers);
    }

    /**
     * 주문을 처리합니다.
     * 1. 재고 확인 및 차감
     * 2. 주문 총액 계산
     * 3. 결제 수단에 따른 결제 처리
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

        // 3. 결제 수단별 할인 적용 및 결제 처리
        int finalAmount = paymentService.calculateFinalAmount(totalAmount, order.getPaymentType());
        paymentService.processPayment(order, finalAmount);
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
