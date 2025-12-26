package phone.v4.main;

import java.time.Duration;

import common.Money;
import phone.v4.domain.Phone;
import phone.v4.policy.additional.AmountDiscountPolicy;
import phone.v4.policy.additional.TaxablePolicy;
import phone.v4.policy.basic.NightlyDiscountPolicy;
import phone.v4.policy.basic.RegularPolicy;

public class MainAgent {
    public static void main(String[] args) {
        Phone phone = new Phone(new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)));
        Phone phone2 = new Phone(new NightlyDiscountPolicy(Money.wons(10), Money.wons(20), Duration.ofSeconds(10)));

        Phone phone3 = new Phone(
            new TaxablePolicy(0.05, // 세액 비율 5% 추가
                new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)) // 세액 추가 전 요금 계산
            )
        );
        Phone phone4 = new Phone(
                    new TaxablePolicy(0.05, // 세액 비율 5% 추가
                        new AmountDiscountPolicy(Money.wons(1000), // 할인 금액 1000원 추가
                            new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))) // 세액 추가 전 요금 계산
                        )
                    );
        Phone phone5 = new Phone(
                    new TaxablePolicy(0.05, // 세액 비율 5% 추가
                        new AmountDiscountPolicy(Money.wons(1000), // 할인 금액 1000원 추가
                            new NightlyDiscountPolicy(Money.wons(10), Money.wons(20), Duration.ofSeconds(10))) // 세액 추가 전 요금 계산
                        )
                    );
    }
}

