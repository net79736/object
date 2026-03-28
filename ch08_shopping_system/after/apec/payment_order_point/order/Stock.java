package ch08_shopping_system.after.apec.payment_order_point.order;

public class Stock {
    private int quantity; // 재고 수량

    public Stock(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    
    /**
     * 재고를 감소시킵니다.
     * @param quantity 감소시킬 수량
     */
    public void decrease(int quantity) {
        // 재고를 감소시킵니다.
        this.quantity -= quantity;
    }

    public boolean isEnough(int quantity) {
        return this.quantity >= quantity;
    }

    /**
     * 재고를 증가시킵니다.
     * @param quantity 증가시킬 수량
     */
    public void increase(int quantity) {
        this.quantity += quantity;
    }
}
