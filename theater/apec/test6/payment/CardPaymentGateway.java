package apec.test6.payment;

import apec.test6.Order;
import apec.test6.payment.info.CardPaymentInfo;
import apec.test6.payment.intf.PaymentGateway;
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
    public void pay(Order order, apec.test6.payment.intf.PaymentInfo paymentInfo, Money amount) {
        // PaymentInfo를 CardPaymentInfo로 캐스팅하여 카드 정보 추출
        CardPaymentInfo cardInfo = (CardPaymentInfo) paymentInfo;
        
        String cardNumber = cardInfo.getCardNumber();
        String cvv = cardInfo.getCvv();
        String cardholderName = cardInfo.getCardholderName();
        
        System.out.println("카드 결제 처리: " + cardNumber + " " + cvv + " " + cardholderName + " " + amount);
    }
}
