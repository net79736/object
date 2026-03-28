package ch04_solid_principles.after.apec.order.payment.info;

import ch04_solid_principles.after.apec.order.PaymentTypeEnum;import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;
/**
 * 계좌이체 결제에 필요한 정보를 담는 클래스
 */
public class AccountPaymentInfo implements PaymentInfo {
    private final String accountNumber;
    private final String bankCode;
    private final String accountHolderName;
    private final PaymentTypeEnum paymentType; // 결제 수단

    public AccountPaymentInfo(String accountNumber, String bankCode, String accountHolderName, PaymentTypeEnum paymentType) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.accountHolderName = accountHolderName;
        this.paymentType = paymentType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    @Override
    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    @Override
    public String getPaymentMethod() {
        return PaymentTypeEnum.BANK_TRANSFER.name();
    }
}

