package apec.test3;

public class Item {
    
    private int price;
    private int quantity;

    public Item(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    
    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}