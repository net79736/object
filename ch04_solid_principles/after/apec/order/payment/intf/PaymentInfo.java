package ch04_solid_principles.after.apec.order.payment.intf;

import ch04_solid_principles.after.apec.order.PaymentTypeEnum;
/**
 * 결제에 필요한 정보를 나타내는 인터페이스
 * 각 결제 수단별로 필요한 정보가 다르므로, 결제 수단별로 구현체를 만듭니다.
 * 
 * 이렇게 분리함으로써:
 * - Customer 클래스가 모든 결제 수단의 정보를 가질 필요가 없음
 * - 새로운 결제 수단 추가 시 Customer를 수정할 필요 없음
 * - 결제 수단 변경이 도메인 모델(Customer)에 영향을 주지 않음
 */
public interface PaymentInfo {
    /**
     * 결제 수단 타입을 반환합니다.
     * @return 결제 수단 타입 (PaymentTypeEnum)
     */
    PaymentTypeEnum getPaymentType();
    
    /**
     * 결제 수단 타입을 문자열로 반환합니다.
     * @return 결제 수단 타입 문자열
     */
    String getPaymentMethod();
}

