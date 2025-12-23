package apec.test6.payment.gateway;

import apec.test6.Order;
import apec.test6.payment.info.WalletPaymentInfo;
import apec.test6.payment.intf.PaymentGateway;
import apec.test6.payment.intf.PaymentInfo;
import common.Money;

/**
 * 지갑(암호화폐) 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: 지갑 결제의 구체적인 처리 방법
 */
public class WalletPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order, PaymentInfo paymentInfo, Money amount) {
        WalletPaymentInfo walletInfo = (WalletPaymentInfo) paymentInfo;
        
        String walletAddress = walletInfo.getWalletAddress();
        System.out.println("지갑 결제 처리: " + walletAddress + " " + amount);
    }
}
