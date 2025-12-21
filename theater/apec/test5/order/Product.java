package apec.test5.order;

public class Product {
    private Long id;
    private Stock stock;
    private String name;
    private int price; // 상품 가격

    public Product(Long id, Stock stock, String name, int price) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    
    public Stock getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void decreaseStock(int quantity) {
        if (!stock.isEnough(quantity)) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        stock.decrease(quantity);
    }
}
