package ch04_solid_principles.after.apec.order.payment.gateway;

import ch04_solid_principles.after.apec.order.Order;import ch04_solid_principles.after.apec.order.payment.info.WalletPaymentInfo;import ch04_solid_principles.after.apec.order.payment.intf.PaymentGateway;import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;import common.Money;

/**
 * 지갑(암호화폐) 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: 지갑 결제의 구체적인 처리 방법
 */
public class WalletPaymentGateway implements PaymentGateway {
    @Override
    public void requestPayment(Order order, PaymentInfo paymentInfo, Money amount) {
        WalletPaymentInfo walletInfo = (WalletPaymentInfo) paymentInfo;
        
        String walletAddress = walletInfo.getWalletAddress();
        System.out.println("지갑 결제 처리: " + walletAddress + " " + amount);
    }
}
