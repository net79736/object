package ch02_discount_policy.before.theater.v1;

import common.Money;

/**
 * ReservationAgency의 책임을 각 객체로 분산
 *   - 할인 조건 검증 → DiscountCondition의 isDiscountable() 메서드로 이동
 *   - 할인 요금 계산 → Movie의 calculateAmountDiscountedFee() 등으로 이동
 *   - 할인 가능 여부 검증 → Movie의 isDiscountable() 메서드로 이동
 *   - 요금 계산 로직 → Screening의 calculateFee() 메서드로 이동
 *   - 새로운 할인 정책을 추가 → Screening의 switch 구문에 새로운 case 절을 추가
 */
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount); // 예매 생성
    }
}
