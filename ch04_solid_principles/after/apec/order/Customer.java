package ch04_solid_principles.after.apec.order;

/**
 * 고객 도메인 모델
 * 
 * 개선 사항:
 * - 결제 수단별 정보를 Customer에서 제거하여 SRP 준수
 * - 결제 수단 변경이 도메인 모델에 영향을 주지 않음
 * - 새로운 결제 수단 추가 시 Customer 클래스를 수정할 필요 없음
 * 
 * 결제 정보는 PaymentInfo 구현체로 분리되어 Order에서 관리됩니다.
 */
public class Customer {
    private Long id;
    private String name;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
