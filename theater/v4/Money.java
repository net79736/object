package v4;

import java.math.BigDecimal;

public class Money {
    // 0원에 대한 반환을 위해
    public static final Money ZERO = Money.wons(0);
    private final BigDecimal amount; // 금액

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 현재 요금에서 추가요금을 계산한다.
     * @param amount
     * @return
     */
    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    /**
     * ### 현재 요금에서 차감요금을 계산한다.
     *
     * @param amount
     * @return
     */
    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    /**
     * ### 현재 요금에서 특정 비율만큼의 요금을 계산한다.
     * @param percent
     * @return
     */
    public Money times(double percent) {
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(percent)));
    }

    /**
     * ### 현재 요금이 인자로 들어온 금액보다 더 작은지 계산한다.
     * @param other
     * @return
     */
    public boolean isLessThan (Money other){
        return amount.compareTo(other.amount) < 0;
    }

    /**
     * ### 현재 금액이 들어온 인자 금액과 같거나 더 큰지 계산한다.
     * @param other
     * @return
     */
    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    /**
     * 현재 금액을 반환한다.
     * @return
     */
    public BigDecimal getAmount() {
        return amount;
    }
}