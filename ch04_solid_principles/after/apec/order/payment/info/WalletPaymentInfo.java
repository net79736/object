package ch04_solid_principles.after.apec.order.payment.info;

import ch04_solid_principles.after.apec.order.PaymentTypeEnum;import ch04_solid_principles.after.apec.order.payment.intf.PaymentInfo;
/**
 * 지갑(암호화폐) 결제에 필요한 정보를 담는 클래스
 */
public class WalletPaymentInfo implements PaymentInfo {
    private final String walletAddress;
    private final PaymentTypeEnum paymentType; // 결제 수단

    public WalletPaymentInfo(String walletAddress, PaymentTypeEnum paymentType) {
        this.paymentType = paymentType;
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    @Override
    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    @Override
    public String getPaymentMethod() {
        return PaymentTypeEnum.WALLET.name();
    }
}

