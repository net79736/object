문제 1-1: 결제 시스템의 OCP 위반
```java
public class PaymentProcessor {

    private 

    public void processPayment(Order order, String paymentType) {
        if (paymentType.equals("CARD")) {
            // 카드 결제 처리
            String cardNumber = order.getCustomer().getCardNumber();
            String cvv = order.getCustomer().getCvv();
            cardGateway.charge(cardNumber, cvv, order.getAmount());
            
        } else if (paymentType.equals("BANK_TRANSFER")) {
            // 계좌이체 처리
            String accountNumber = order.getCustomer().getAccountNumber();
            String bankCode = order.getCustomer().getBankCode();
            bankGateway.transfer(accountNumber, bankCode, order.getAmount());
            
        } else if (paymentType.equals("PAYPAL")) {
            // PayPal 처리
            String email = order.getCustomer().getEmail();
            paypalGateway.pay(email, order.getAmount());
            
        } else if (paymentType.equals("CRYPTO")) {
            // 암호화폐 처리
            String walletAddress = order.getCustomer().getWalletAddress();
            cryptoGateway.sendPayment(walletAddress, order.getAmount());
        }
        
        order.setStatus("PAID");
        order.setPaymentDate(LocalDateTime.now());
    }
}
```
질문:

이 코드가 OCP를 위반하는 구체적인 이유 3가지를 설명하시오
새로운 결제 수단(예: Apple Pay, 토스페이)을 추가할 때 어떤 문제가 발생하는가?
"컴파일타임 의존성 vs 런타임 의존성" 관점에서 이 코드를 분석하시오