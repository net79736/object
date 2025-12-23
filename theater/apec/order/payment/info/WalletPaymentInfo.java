package apec.test6.payment.info;

import static apec.test6.PaymentTypeEnum.WALLET;
import apec.test6.payment.intf.PaymentInfo;

/**
 * 지갑(암호화폐) 결제에 필요한 정보를 담는 클래스
 */
public class WalletPaymentInfo implements PaymentInfo {
    private final String walletAddress;

    public WalletPaymentInfo(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    @Override
    public String getPaymentMethod() {
        return WALLET.name();
    }
}

