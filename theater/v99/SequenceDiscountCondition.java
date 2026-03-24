package v99;

import v0.Screening;

public class SequenceDiscountCondition implements DiscountCondition {
    private int sequence;

    public SequenceDiscountCondition(int sequence) {
        this.sequence = sequence;
    }

    /**
     * 할인 조건 검증
     * 특정 상영 순서에 따른 할인 조건을 검증
     * 
     * @param screening 상영 정보 (상영 순서 정보)
     * @return 할인 가능 여부 (true: 할인 가능, false: 할인 불가능)
     */
    @Override
    public boolean isDiscountable(Screening screening) {
        return screening.getSequence() == sequence; // 상영 순서와 순서 할인 조건이 같은지 확인
    }
}
