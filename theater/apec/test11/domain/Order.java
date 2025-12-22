package apec.test11.domain;

import apec.test11.dto.OrderRequest;
import apec.test11.paymentinfo.PaymentInfo;

public class Order {

    private PaymentInfo paymentInfo;
    
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
    
    public static Order from(OrderRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'from'");
    }
}
