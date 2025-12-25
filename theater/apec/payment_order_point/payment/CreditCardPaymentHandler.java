package apec.payment_order_point.payment;

import apec.payment_order_point.common.User;
import apec.payment_order_point.main.PaymentType;
import apec.payment_order_point.payment.intf.PaymentHandler;

/**
 * 신용카드 결제를 처리하는 핸들러
 * 
 * 실제 구현에서는 외부 카드 결제 API를 호출합니다.
 * 할인 정책은 PaymentDiscountPolicy로 분리되어 있습니다.
 */
public class CreditCardPaymentHandler implements PaymentHandler {
    
    @Override
    public void processPayment(User user, int amount) {
        // 실제 구현에서는 카드사 API 호출
        // 여기서는 시뮬레이션만 수행
        System.out.println("신용카드 결제 처리: " + amount + "원");
    }
    
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CREDIT_CARD;
    }
}

