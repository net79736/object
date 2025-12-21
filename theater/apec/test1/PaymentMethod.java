package apec.test1;

import common.Money;

public class PaymentMethod {
    private String name;
    private String cardNumber;
    private String cvv;
    private String expirationDate;

    public PaymentMethod(String name, String cardNumber, String cvv, String expirationDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public void charge(Money totalAmount) {
        // 결제 처리
        System.out.println("결제 처리: " + totalAmount.getAmount());
    }
}
