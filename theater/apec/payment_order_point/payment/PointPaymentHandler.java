package apec.payment_order_point.payment;

import apec.payment_order_point.common.User;
import apec.payment_order_point.main.PaymentType;
import apec.payment_order_point.payment.intf.PaymentHandler;
import apec.payment_order_point.point.PointService;

/**
 * 포인트 결제를 처리하는 핸들러
 * 
 * 사용자의 포인트를 차감하여 결제를 처리합니다.
 */
public class PointPaymentHandler implements PaymentHandler {
    private final PointService pointService;
    
    public PointPaymentHandler(PointService pointService) {
        this.pointService = pointService;
    }
    
    @Override
    public void processPayment(User user, int amount) {
        // 포인트 사용
        pointService.usePoint(user, amount);
        System.out.println("포인트 결제 처리: " + amount + "포인트");
    }
    
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.POINT;
    }
    
    @Override
    public int getDiscountRate() {
        return PaymentType.POINT.getDiscountRate();
    }
}

