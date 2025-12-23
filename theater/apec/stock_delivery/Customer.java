package apec.test1;

import java.util.List;

import common.Money;

public class Customer {
    private Long id;
    private String name;
    private Grade grade; // 고객 등급
    private List<PaymentMethod> paymentMethods;    // 결제 수단 리스트(카드, 계좌이체, 휴대폰 결제 등)

    public Customer(Long id, String name, Grade grade, List<PaymentMethod> paymentMethods) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.paymentMethods = paymentMethods;
    }

    public Long getId() {
        return id;
    }

    public String getName() {   
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }   

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    /**
     * 고객이 VIP인지 여부를 반환합니다.
     * @return 고객이 VIP인지 여부
     */
    public boolean isVipCustomer() {
        return grade != null && grade.isVip();
    }

    public void pay(Money totalAmount) {
        if (paymentMethods == null || paymentMethods.isEmpty()) {
            throw new RuntimeException("결재 수단이 없습니다.");
        }

        // 기본 결제수단 선택 (첫 번째 결제수단 사용)
        // 향후 기본 결제수단 선택 로직으로 확장 가능
        PaymentMethod payment = paymentMethods.get(0);
        payment.charge(totalAmount);
    }
}
