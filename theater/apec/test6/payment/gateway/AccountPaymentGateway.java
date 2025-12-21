package apec.test6.payment.gateway;

import apec.test6.Order;
import apec.test6.payment.info.AccountPaymentInfo;
import apec.test6.payment.intf.PaymentGateway;
import apec.test6.payment.intf.PaymentInfo;
import common.Money;

/**
 * 계좌이체 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: 계좌이체 결제의 구체적인 처리 방법
 */
public class AccountPaymentGateway implements PaymentGateway {
    @Override
    public void pay(Order order, PaymentInfo paymentInfo, Money amount) {
        AccountPaymentInfo accountInfo = (AccountPaymentInfo) paymentInfo;
        
        String accountNumber = accountInfo.getAccountNumber();
        String bankCode = accountInfo.getBankCode();
        String accountHolderName = accountInfo.getAccountHolderName();
        
        System.out.println("계좌이체 결제 처리: " + accountNumber + " " + bankCode + " " + accountHolderName + " " + amount);
    }
}
