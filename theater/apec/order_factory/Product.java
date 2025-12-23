package apec.test8;

import apec.test5.order.Stock;

public class Product {
    private Long id;
    private String name;
    private int price;
    private Stock stock;

    public Product(Long id, String name, int price, Stock stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (!stock.isEnough(quantity)) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        stock.decrease(quantity);
    }
}
