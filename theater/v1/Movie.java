package v1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import common.Money;

public class Movie {
    private String title; // 영화 제목
    private Duration runningTime; // 영화 총 시간
    private Money fee; // 요금

    // 할인 조건
    private List<DiscountCondition> discountConditions;

    // 영화 할인 타입 (AMOUNT_DISCOUNT, PERCENT_DISCOUNT, NONE_DISCOUNT)
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    /**
     * 영화 할인 타입 반환
     *   - AMOUNT_DISCOUNT: 금액 할인
     *   - PERCENT_DISCOUNT: 비율 할인
     *   - NONE_DISCOUNT: 미적용
     * @return 영화 할인 타입
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * 금액 할인 요금 계산
     * 
     * @return 금액 할인 요금
     */
    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    /**
     * 비율 할인 요금 계산
     * 
     * @return 비율 할인 요금
     */
    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    } 

    /**
     * 미적용 할인 요금 계산
     * 
     * @return 미적용 할인 요금
     */
    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee;
    }

    /**
     * 할인 가능 여부 검증
     * @param whenScreened 상영 시작 시간
     * @param sequence 상영 순서
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                if (condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }
}