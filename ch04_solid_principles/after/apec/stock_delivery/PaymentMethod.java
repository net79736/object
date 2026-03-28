package ch04_solid_principles.after.apec.stock_delivery;

import common.Money;

public class PaymentMethod {
    private String name; // 결제 수단 이름(카드, 계좌이체, 휴대폰 결제 등)
    private String cardNumber; // 카드 번호
    private String cvv; // 카드 비밀번호
    private String expirationDate; // 카드 만료일

    public PaymentMethod(String name, String cardNumber, String cvv, String expirationDate) {
        this.name = name; // 결제 수단 이름
        this.cardNumber = cardNumber; // 카드 번호
        this.cvv = cvv; // 카드 비밀번호
        this.expirationDate = expirationDate; // 카드 만료일
    }

    public void charge(Money totalAmount) {
        // 결제 처리
        System.out.println("결제 처리: " + totalAmount.getAmount());
    }
}
