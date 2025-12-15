package v0;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import v0.condition.DiscountCondition;
import v0.condition.DiscountConditionType;

public class Movie {
    private String title; // 영화 제목
    private Duration runningTime; // 영화 총 시간
    private Money fee; // 요금
    private List<DiscountCondition> discountConditions; // 할인 조건
    
    private MovieType movieType; // 영화 타입
    private Money discountAmount; // 금액 할인
    private double discountPercent; // 비율 할인

    public MovieType getMovieType() {
        return movieType;
    }

    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    }   

    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee;
    }

    /**
     * 할인 조건을 확인한다.
     * @param whenScreened 상영 시간 (yyyy-MM-dd HH:mm)
     * @param sequence 상영 순번 
     * @return 할인 조건을 만족하면 true, 그렇지 않으면 false
     */
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                // 기간 조건을 확인한다.
                if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                // 순번 조건을 확인한다.
                if (condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }
}
