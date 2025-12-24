package apec.order.payment.gateway;

import apec.order.Order;
import apec.order.payment.info.CardPaymentInfo;
import apec.order.payment.intf.PaymentGateway;
import apec.order.payment.intf.PaymentInfo;
import common.Money;

/**
 * 카드 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: 카드 결제의 구체적인 처리 방법
 * - Customer에 의존하지 않고 PaymentInfo를 통해 필요한 정보만 받음
 * - 결제 수단 변경이 도메인 모델에 영향을 주지 않음
 */
public class CardPaymentGateway implements PaymentGateway {
    @Override
    public void requestPayment(Order order, PaymentInfo paymentInfo, Money amount) {
        // PaymentInfo를 CardPaymentInfo로 캐스팅하여 카드 정보 추출
        CardPaymentInfo cardInfo = (CardPaymentInfo) paymentInfo; // 캐스팅 필요
        
        String cardNumber = cardInfo.getCardNumber(); // 카드번호
        String cvv = cardInfo.getCvv(); // 카드비밀번호
        String cardholderName = cardInfo.getCardholderName(); // 카드소유자명
        
        System.out.println("카드 결제 처리: " + cardNumber + " " + cvv + " " + cardholderName + " " + amount);
    }
}
