package apec.discount;

import java.util.List;

import apec.discount.domain.Item;
import apec.discount.domain.User;

/**
 * 장바구니 클래스
 * 장바구니에 담긴 상품들의 총 금액을 계산하고, 할인을 적용합니다.
 * 
 * 책임:
 * - 장바구니 상품 관리
 * - 상품 총 금액 계산
 * - 할인 계산기에게 할인 계산 위임
 */
public class ShoppingCart {
    private List<Item> items;
    private User user;
    private DiscountCalculator discountCalculator;

    public ShoppingCart(List<Item> items, User user) {
        this.items = items;
        this.user = user;
        this.discountCalculator = new DiscountCalculator();
    }

    /**
     * 장바구니의 최종 금액을 계산합니다.
     * 
     * 1. 상품들의 총 금액을 계산합니다.
     * 2. DiscountCalculator에게 사용자 정보와 총 금액을 전달하여 할인을 적용합니다.
     * 
     * @return 할인 적용 후 최종 금액
     */
    public int calculateTotal() {
        // 총 금액 계산
        int total = sumTotalPrice();
        
        // 할인 계산기에게 할인 적용을 위임
        // ShoppingCart는 할인 계산 로직을 알 필요 없이
        // DiscountCalculator에게 위임합니다.
        return discountCalculator.calculateFinalTotal(user, total);
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
