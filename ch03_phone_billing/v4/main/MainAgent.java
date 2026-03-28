package ch03_phone_billing.v4.main;

import java.time.Duration;
import common.Money;
import ch03_phone_billing.v4.domain.Phone;import ch03_phone_billing.v4.policy.additional.AmountDiscountPolicy;import ch03_phone_billing.v4.policy.additional.TaxablePolicy;import ch03_phone_billing.v4.policy.basic.NightlyDiscountPolicy;import ch03_phone_billing.v4.policy.basic.RegularPolicy;
public class MainAgent {
    public static void main(String[] args) {
        // ============================================
        // BasicRatePolicy만 사용하는 케이스
        // ============================================
        Phone phone1 = new Phone(
            new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))
        );
        
        Phone phone2 = new Phone(
            new NightlyDiscountPolicy(Money.wons(10), Money.wons(20), Duration.ofSeconds(10))
        );

        // ============================================
        // AdditionalRatePolicy + BasicRatePolicy 조합 케이스
        // ============================================
        
        // 세액 추가 (RegularPolicy 기반)
        Phone phone3 = new Phone(
            new TaxablePolicy(0.05,
                new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))
            )
        );
        
        // 세액 추가 + 할인 (RegularPolicy 기반)
        Phone phone4 = new Phone(
            new TaxablePolicy(0.05,
                new AmountDiscountPolicy(Money.wons(1000),
                    new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))
                )
            )
        );
        
        // 세액 추가 + 할인 (NightlyDiscountPolicy 기반)
        Phone phone5 = new Phone(
            new TaxablePolicy(0.05,
                new AmountDiscountPolicy(Money.wons(1000),
                    new NightlyDiscountPolicy(Money.wons(10), Money.wons(20), Duration.ofSeconds(10))
                )
            )
        );
    }
}

