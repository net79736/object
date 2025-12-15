package v4;

import java.time.Duration;

import v4.policy.DiscountPolicy;

/*
 * 영화 정보를 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-04
 */
public class Movie {
    private String title; // 영화 제목
    private Duration runningTime; // 상영 시간
    private Money fee; // 영화 요금
    private DiscountPolicy discountPolicy; // 할인 정책에 대한 추상화

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee () {
        return fee;
    }

    /**
     * 영화 요금을 계산한다.
     * @param screening 상영 정보
     * @return
     */
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(
            discountPolicy.calculateDiscountAmount(screening)
        ); // 할인 금액을 차감한 영화 요금을 반환한다.
    }
}
