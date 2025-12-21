package apec.test5.order;

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
