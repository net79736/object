package ch01_basic_oop.after.theater.v2.condition;

import ch01_basic_oop.after.theater.v2.Screening;
public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
