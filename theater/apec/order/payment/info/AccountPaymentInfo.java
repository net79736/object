package apec.order.payment.info;

import apec.order.PaymentTypeEnum;
import apec.order.payment.intf.PaymentInfo;

/**
 * 계좌이체 결제에 필요한 정보를 담는 클래스
 */
public class AccountPaymentInfo implements PaymentInfo {
    private final String accountNumber;
    private final String bankCode;
    private final String accountHolderName;

    public AccountPaymentInfo(String accountNumber, String bankCode, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.accountHolderName = accountHolderName;
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
    public String getPaymentMethod() {
        return PaymentTypeEnum.BANK_TRANSFER.name();
    }
}

