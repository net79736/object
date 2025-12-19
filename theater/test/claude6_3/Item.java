package test.claude6_3;

import java.util.List;

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

    public static int calculatePriceTotal(List<Item> items) {
        return items.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
