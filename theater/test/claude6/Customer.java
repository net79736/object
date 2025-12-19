package test.claude6;

import java.util.List;

public class Customer {

    private Grade grade;
    private List<PaymentMethod> paymentMethods;

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    // Customer.java 수정
    public boolean isVipCustomer() {
        return grade != null && grade.isVip();
    }

    /**
     * 주문 금액을 결제합니다.
     * Customer가 자신의 결제수단을 선택하고 결제를 처리합니다.
     * 묻지 말고 시켜라 원칙: Customer가 자신의 결제를 처리합니다.
     * 
     * @param amount 결제할 금액
     * @throws RuntimeException 결제수단이 없는 경우
     */
    public void pay(int amount) {
        if (paymentMethods == null || paymentMethods.isEmpty()) {
            throw new RuntimeException("No payment method available");
        }
        // 기본 결제수단 선택 (첫 번째 결제수단 사용)
        // 향후 기본 결제수단 선택 로직으로 확장 가능
        PaymentMethod payment = paymentMethods.get(0);
        payment.charge(amount);
    }
}
