package ch04_solid_principles.after.apec.order.payment.factory;

import ch04_solid_principles.after.apec.order.PaymentTypeEnum;import ch04_solid_principles.after.apec.order.payment.gateway.AccountPaymentGateway;import ch04_solid_principles.after.apec.order.payment.gateway.CardPaymentGateway;import ch04_solid_principles.after.apec.order.payment.gateway.PaypalPaymentGateway;import ch04_solid_principles.after.apec.order.payment.gateway.WalletPaymentGateway;import ch04_solid_principles.after.apec.order.payment.intf.PaymentGateway;
/**
 * PaymentType에 따라 적절한 PaymentGateway 구현체를 생성하는 팩토리
 * 새로운 결제 수단 추가 시 여기에만 추가하면 됨 (OCP 준수)
 */
public class PaymentGatewayFactory {
    
    /**
     * PaymentType에 해당하는 PaymentGateway 구현체를 반환합니다.
     * 
     * @param paymentType 결제 수단 타입
     * @return 해당 결제 수단의 PaymentGateway 구현체
     * @throws IllegalArgumentException 지원하지 않는 결제 수단인 경우
     */
    public static PaymentGateway create(PaymentTypeEnum paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("결제 수단은 null일 수 없습니다.");
        }
        
        switch (paymentType) {
            case CARD:
                return new CardPaymentGateway();
            case BANK_TRANSFER:
                return new AccountPaymentGateway();
            case PAYPAL:
                return new PaypalPaymentGateway();
            case WALLET:
                return new WalletPaymentGateway();
            default:
                throw new IllegalArgumentException("지원하지 않는 결제 수단입니다: " + paymentType);
        }
    }
}

