package v4.condition;

import v4.Screening;

/**
 * 상영 순번에 따른 할인 조건을 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-04
 */
public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    /**
     * 상영 순번에 따른 할인 조건을 확인한다.
     * @param screening 상영 정보
     * @return
     */
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence); // 상영 순번이 일치하면 true를 반환한다.
    }
}
