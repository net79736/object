package apec.test1;

import test.claude6_1.Stock;

public class Product {

    private Long id;
    private String name;
    private Stock stock;

    public Product(Long id, String name, Stock stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public boolean isOutOfStock(int quantity) {
        return stock.getQuantity() < quantity;
    }

    public void decreaseStock(int quantity) {
        if (isOutOfStock(quantity)) {
            throw new RuntimeException("Out of stock");
        }
        stock.decrease(quantity);
    }
}
