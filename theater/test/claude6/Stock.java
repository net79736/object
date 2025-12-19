package test.claude6;

public class Stock {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decrease(int quantity) {
        this.quantity -= quantity;
    }
}
