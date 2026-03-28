package ch02_discount_policy.after.theater.v2.condition;

import ch02_discount_policy.after.theater.v2.Screening;
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
