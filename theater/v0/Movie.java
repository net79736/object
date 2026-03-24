package v0;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import common.Money;
import v99.DiscountPolicy;

public class Movie {
    private String title; // 영화 제목
    private Duration runningTime; // 영화 총 시간
    private Money fee; // 요금

    private List<DiscountCondition> discountConditions; // 할인 조건
    private MovieType movieType; // 영화 할인 타입 (AMOUNT_DISCOUNT, PERCENT_DISCOUNT, NONE_DISCOUNT)
    private Money discountAmount; // 금액 할인
    private double discountPercent; // 비율 할인

    private DiscountPolicy discountPolicy; // 할인 정책

    public MovieType getMovieType() {
        return movieType;
    }
    public void setMovieType (MovieType movieType) {
        this.movieType = movieType;
    }
    public Money getFee() {
        return fee;
    }
    public void setFee (Money fee) {
        this.fee = fee;
    }
    public List<DiscountCondition> getDiscountConditions() {
        return Collections.unmodifiableList(discountConditions);
    }
    public void setDiscountConditions(
            List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }
    public Money getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount (Money discountAmount) {
        this.discountAmount = discountAmount;
    }
    public double getDiscountPercent () {
        return discountPercent;
    }
    public void setDiscountPercent (double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}