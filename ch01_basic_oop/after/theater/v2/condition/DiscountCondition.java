package ch01_basic_oop.after.theater.v2.condition;

import ch01_basic_oop.after.theater.v2.Screening;
public interface DiscountCondition {

    /**
     * 할인 조건 검증
     * @param screening 상영 정보
     * @return 할인 조건 만족 여부
     */
    public boolean isSatisfiedBy(Screening screening);
}
