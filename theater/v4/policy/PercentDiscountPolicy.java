package v4.policy;

import v4.Money;
import v4.Screening;
import v4.condition.DiscountCondition;

/**
 * 비율 할인 정책을 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-04
 */
public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent; // 할인 비율

    /**
     * 생성자로 초기화된 비율 할인 정책을 반환한다.
     * @param percent 할인 비율
     * @param conditions 할인 조건
     */
    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    /**
     * 비율 할인 정책을 적용한다.
     * @param screening 상영 정보
     * @return
     */
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}