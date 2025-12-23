package apec.test5.payment;

import apec.test5.common.User;
import apec.test5.main.PaymentType;
import apec.test5.payment.intf.PaymentHandler;

/**
 * 계좌이체 결제를 처리하는 핸들러
 * 
 * 실제 구현에서는 은행 API를 호출합니다.
 */
public class AccountTransferPaymentHandler implements PaymentHandler {
    
    @Override
    public void processPayment(User user, int amount) {
        // 실제 구현에서는 은행 API 호출
        // 여기서는 시뮬레이션만 수행
        System.out.println("계좌이체 결제 처리: " + amount + "원");
    }
    
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.ACCOUNT_TRANSFER;
    }
    
    @Override
    public int getDiscountRate() {
        return PaymentType.ACCOUNT_TRANSFER.getDiscountRate();
    }
}

