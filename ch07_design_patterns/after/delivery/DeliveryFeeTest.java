package ch07_design_patterns.after.delivery;

import ch07_design_patterns.after.delivery.common.DeliveryFeePolicyChain;import ch07_design_patterns.after.delivery.policy.ExpressDeliveryPolicy;import ch07_design_patterns.after.delivery.policy.NightTimeDeliveryPolicy;import ch07_design_patterns.after.delivery.policy.RainyDayDeliveryPolicy;import ch07_design_patterns.after.delivery.policy.WeekendDeliveryPolicy;
/**
 * 배송비 계산 시스템 테스트 클래스
 * 
 * 상속 기반 설계에서 합성 기반 설계로 리팩토링한 결과를 검증합니다.
 * 다양한 정책 조합을 테스트합니다.
 */
public class DeliveryFeeTest {
    
    public static void main(String[] args) {
        DeliveryFeeTest test = new DeliveryFeeTest();
        
        System.out.println("=== 배송비 계산 시스템 테스트 (합성 기반 설계) ===\n");
        
        test.test1_BaseDeliveryFee();
        test.test2_ExpressDelivery();
        test.test3_ExpressRainyNightDelivery();
        test.test4_WeekendExpressDelivery();
        test.test5_PolicyOrderTest();
        test.test6_VariousCombinations();
    }
    
    /**
     * 테스트 1: 기본 배송비 계산
     */
    private void test1_BaseDeliveryFee() {
        System.out.println("--- 테스트 1: 기본 배송비 계산 (거리 5km) ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        int fee = calculator.calculate(5);
        
        System.out.println("거리: 5km");
        System.out.println("기본 배송비: " + fee + "원");
        System.out.println("예상: 5,000원 (5km * 1,000원)\n");
    }
    
    /**
     * 테스트 2: 특급 배송비 계산
     */
    private void test2_ExpressDelivery() {
        System.out.println("--- 테스트 2: 특급 배송비 계산 (거리 5km) ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        int fee = calculator.calculate(5, new ExpressDeliveryPolicy());
        
        System.out.println("거리: 5km");
        System.out.println("정책: 특급 배송");
        System.out.println("최종 배송비: " + fee + "원");
        System.out.println("예상: 10,000원 (5,000원 + 5,000원)\n");
    }
    
    /**
     * 테스트 3: 특급 + 우천 + 심야 배송비 계산
     * 요구사항: 특급+우천+심야 배송비를 계산하는 객체 생성 코드
     */
    private void test3_ExpressRainyNightDelivery() {
        System.out.println("--- 테스트 3: 특급 + 우천 + 심야 배송비 계산 (거리 5km) ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        
        // 방법 1: 가변 인자 사용
        int fee1 = calculator.calculate(
            5,
            new ExpressDeliveryPolicy(),
            new RainyDayDeliveryPolicy(),
            new NightTimeDeliveryPolicy()
        );
        
        System.out.println("거리: 5km");
        System.out.println("정책: 특급 + 우천 + 심야");
        System.out.println("최종 배송비 (가변 인자): " + fee1 + "원");
        System.out.println("계산 과정:");
        System.out.println("  기본: 5,000원");
        System.out.println("  + 특급: 5,000원 → 10,000원");
        System.out.println("  + 우천: 3,000원 → 13,000원");
        System.out.println("  + 심야: 7,000원 → 20,000원");
        System.out.println("예상: 20,000원\n");
        
        // 방법 2: 체인 사용 (더 명시적이고 확장 가능)
        DeliveryFeePolicyChain chain = new DeliveryFeePolicyChain()
            .addPolicy(new ExpressDeliveryPolicy())
            .addPolicy(new RainyDayDeliveryPolicy())
            .addPolicy(new NightTimeDeliveryPolicy());
        
        int fee2 = calculator.calculate(5, chain);
        System.out.println("최종 배송비 (체인 사용): " + fee2 + "원");
        System.out.println("예상: 20,000원\n");
    }
    
    /**
     * 테스트 4: 주말 + 특급 배송비 계산
     */
    private void test4_WeekendExpressDelivery() {
        System.out.println("--- 테스트 4: 주말 + 특급 배송비 계산 (거리 5km) ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        
        // 주말 할증을 먼저 적용한 경우
        int fee1 = calculator.calculate(
            5,
            new WeekendDeliveryPolicy(),
            new ExpressDeliveryPolicy()
        );
        
        System.out.println("거리: 5km");
        System.out.println("정책 순서: 주말 할증 → 특급 배송");
        System.out.println("최종 배송비: " + fee1 + "원");
        System.out.println("계산 과정:");
        System.out.println("  기본: 5,000원");
        System.out.println("  주말 할증 (50%): 5,000원 * 1.5 = 7,500원");
        System.out.println("  + 특급: 5,000원 → 12,500원");
        System.out.println("예상: 12,500원\n");
        
        // 특급 배송을 먼저 적용한 경우 (순서 변경)
        int fee2 = calculator.calculate(
            5,
            new ExpressDeliveryPolicy(),
            new WeekendDeliveryPolicy()
        );
        
        System.out.println("정책 순서: 특급 배송 → 주말 할증");
        System.out.println("최종 배송비: " + fee2 + "원");
        System.out.println("계산 과정:");
        System.out.println("  기본: 5,000원");
        System.out.println("  + 특급: 5,000원 → 10,000원");
        System.out.println("  주말 할증 (50%): 10,000원 * 1.5 = 15,000원");
        System.out.println("예상: 15,000원");
        System.out.println("→ 정책 순서에 따라 결과가 달라짐을 확인!\n");
    }
    
    /**
     * 테스트 5: 정책 순서 변경 테스트
     */
    private void test5_PolicyOrderTest() {
        System.out.println("--- 테스트 5: 정책 순서 변경 테스트 (거리 10km) ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        
        // 순서 1: 특급 → 우천 → 주말
        DeliveryFeePolicyChain chain1 = new DeliveryFeePolicyChain()
            .addPolicy(new ExpressDeliveryPolicy())
            .addPolicy(new RainyDayDeliveryPolicy())
            .addPolicy(new WeekendDeliveryPolicy());
        
        int fee1 = calculator.calculate(10, chain1);
        System.out.println("순서 1: 특급 → 우천 → 주말");
        System.out.println("  기본: 10,000원");
        System.out.println("  + 특급: 5,000원 → 15,000원");
        System.out.println("  + 우천: 3,000원 → 18,000원");
        System.out.println("  주말 할증 (50%): 18,000원 * 1.5 = 27,000원");
        System.out.println("  최종: " + fee1 + "원\n");
        
        // 순서 2: 주말 → 특급 → 우천
        DeliveryFeePolicyChain chain2 = new DeliveryFeePolicyChain()
            .addPolicy(new WeekendDeliveryPolicy())
            .addPolicy(new ExpressDeliveryPolicy())
            .addPolicy(new RainyDayDeliveryPolicy());
        
        int fee2 = calculator.calculate(10, chain2);
        System.out.println("순서 2: 주말 → 특급 → 우천");
        System.out.println("  기본: 10,000원");
        System.out.println("  주말 할증 (50%): 10,000원 * 1.5 = 15,000원");
        System.out.println("  + 특급: 5,000원 → 20,000원");
        System.out.println("  + 우천: 3,000원 → 23,000원");
        System.out.println("  최종: " + fee2 + "원");
        System.out.println("→ 정책 순서에 따라 최종 금액이 달라짐!\n");
    }
    
    /**
     * 테스트 6: 다양한 정책 조합 테스트
     */
    private void test6_VariousCombinations() {
        System.out.println("--- 테스트 6: 다양한 정책 조합 테스트 ---");
        
        DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
        
        // 조합 1: 일반 배송 (정책 없음)
        int fee1 = calculator.calculate(3);
        System.out.println("조합 1: 일반 배송 (3km)");
        System.out.println("  최종: " + fee1 + "원\n");
        
        // 조합 2: 특급 + 심야
        int fee2 = calculator.calculate(
            3,
            new ExpressDeliveryPolicy(),
            new NightTimeDeliveryPolicy()
        );
        System.out.println("조합 2: 특급 + 심야 (3km)");
        System.out.println("  최종: " + fee2 + "원\n");
        
        // 조합 3: 우천 + 심야
        int fee3 = calculator.calculate(
            3,
            new RainyDayDeliveryPolicy(),
            new NightTimeDeliveryPolicy()
        );
        System.out.println("조합 3: 우천 + 심야 (3km)");
        System.out.println("  최종: " + fee3 + "원\n");
        
        // 조합 4: 모든 정책 적용
        int fee4 = calculator.calculate(
            3,
            new ExpressDeliveryPolicy(),
            new WeekendDeliveryPolicy(),
            new RainyDayDeliveryPolicy(),
            new NightTimeDeliveryPolicy()
        );
        System.out.println("조합 4: 모든 정책 (특급 + 주말 + 우천 + 심야, 3km)");
        System.out.println("  최종: " + fee4 + "원\n");
    }
}

