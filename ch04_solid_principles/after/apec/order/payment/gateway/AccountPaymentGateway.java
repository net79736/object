package ch04_solid_principles.after.apec.order.payment.gateway;

import ch04_solid_principles.after.apec.order.Order;import ch04_solid_principles.after.apec.order.payment.info.AccountPaymentInfo;import ch04_solid_principles.after.apec.order.payment.intf.PaymentGateway;import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;import common.Money;

/**
 * 계좌이체 결제를 처리하는 PaymentGateway 구현체
 * 
 * 변하는 부분: 계좌이체 결제의 구체적인 처리 방법
 */
public class AccountPaymentGateway implements PaymentGateway {
    @Override
    public void requestPayment(Order order, PaymentInfo paymentInfo, Money amount) {
        AccountPaymentInfo accountInfo = (AccountPaymentInfo) paymentInfo;
        
        String accountNumber = accountInfo.getAccountNumber(); // 계좌번호
        String bankCode = accountInfo.getBankCode(); // 은행코드
        String accountHolderName = accountInfo.getAccountHolderName(); // 소유주명
        
        System.out.println("계좌이체 결제 처리: " + accountNumber + " " + bankCode + " " + accountHolderName + " " + amount);
    }
}
