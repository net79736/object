package apec.order.payment.gateway;

import apec.order.Order;
import apec.order.payment.info.AccountPaymentInfo;
import apec.order.payment.intf.PaymentGateway;
import apec.order.payment.intf.PaymentInfo;
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
