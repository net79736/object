package ch01_basic_oop.after.theater.v2;

import java.time.Duration;

import common.Money;
import ch01_basic_oop.after.theater.v2.policy.DiscountPolicy;
public class Movie {
    private String title; // 영화 제목
    private Duration runningTime; // 상영 시간
    private Money fee; // 요금
    private DiscountPolicy discountPolicy; // 할인 정책 인터페이스

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
