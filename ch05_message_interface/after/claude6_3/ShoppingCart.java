package ch05_message_interface.after.claude6_3;

import java.util.List;

/**
 * 이 코드의 문제점을 3가지 이상 나열하시오
 * "묻지말고 시켜라" 원칙을 적용하여 리팩토링하시오
 * 새로운 할인 정책 추가가 쉬운 구조로 만드시오
 */
public class ShoppingCart {
    private List<Item> items;
    private User user;
    
    /**
     * 장바구니의 최종 금액을 계산합니다.
     * 묻지 말고 시켜라 원칙: User가 자신의 할인을 계산합니다.
     * 
     * @return 할인 적용 후 최종 금액
     */
    public int calculateTotal() {
        // 총 금액 계산
        int total = Item.calculatePriceTotal(items);
        
        // 사용자의 모든 할인 정책을 적용하여 최종 금액 계산
        return user.calculateFinalTotal(total);
    }
}