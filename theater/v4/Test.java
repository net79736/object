package v4;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import v4.condition.PeriodCondition;
import v4.condition.SequenceCondition;
import v4.policy.AmountDiscountPolicy;
import v4.policy.PercentDiscountPolicy;

public class Test {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");        

        // Money money = Money.wons(10000); // 10000원

        // Money amount = money.times(0.3); // 3000원
        // System.out.println(amount.getAmount());

        Movie avatar = new Movie(
            "아바타", // 영화 제목
            Duration.ofMinutes(120), // 상영 시간
            Money.wons(10000), // 영화 요금
            new AmountDiscountPolicy( // 고정 금액 할인 정책
                Money.wons(800), // 할인 금액
                new SequenceCondition(1), // 상영 순번 1번
                new SequenceCondition(10) // 상영 순번 10번
            )
        );

        Movie titanic = new Movie(
            "타이타닉",
            Duration.ofMinutes(180), // 상영 시간
            Money.wons(11000), // 영화 요금
            new PercentDiscountPolicy(
                0.1, // 할인 비율 10%
                new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of (14, 0), LocalTime.of (16, 59)), // 화요일 14시 0분 ~ 16시 59분
                new SequenceCondition(2), // 상영 순번 2번
                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(13, 59)) // 목요일 10시 0분 ~ 13시 59분
        ));
    }
}
