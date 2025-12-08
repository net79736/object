package v4.condition;

import v4.Screening;

public interface DiscountCondition {
    /**
     * 할인 조건을 만족하는지 확인
     * @param screening 상영 정보
     * @return
     */
	boolean isSatisfiedBy(Screening screening);
}