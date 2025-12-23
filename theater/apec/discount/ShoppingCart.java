package apec.discount;

import java.util.List;

/**
 * 장바구니 클래스
 * "묻지 말고 시켜라" 원칙: User가 자신의 할인을 계산하도록 위임합니다.
 */
public class ShoppingCart {
    private List<Item> items;
    private User user;

    public ShoppingCart(List<Item> items, User user) {
        this.items = items;
        this.user = user;
    }

    /**
     * 장바구니의 최종 금액을 계산합니다.
     * 묻지 말고 시켜라 원칙: User가 자신의 할인을 계산합니다.
     * 
     * @return 할인 적용 후 최종 금액
     */
    public int calculateTotal() {
        // 총 금액 계산
        int total = sumTotalPrice();
        
        // 사용자의 모든 할인 정책을 적용하여 최종 금액 계산
        // ShoppingCart는 User의 내부 상태를 알 필요 없이
        // User에게 최종 금액 계산을 위임합니다.
        return user.calculateFinalTotal(total);
    }

    /**
     * 장바구니에 담긴 모든 상품의 총 금액을 계산합니다.
     * 
     * @return 상품 총 금액
     */
    private int sumTotalPrice() {
        int total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
