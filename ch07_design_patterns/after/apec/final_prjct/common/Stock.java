package ch07_design_patterns.after.apec.final_prjct.common;

/**
 * 재고 클래스
 * 재고 관리를 위한 클래스
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class Stock {
    private int quantity;

    public Stock(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void decrease(int quantity) {
        // 재고를 감소시킵니다.
        this.quantity -= quantity;
    }

    public boolean isEnough(int quantity) {
        return this.quantity >= quantity;
    }
}
