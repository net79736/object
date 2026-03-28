package ch04_solid_principles.after.apec.stock_delivery;

import ch05_message_interface.after.claude6_1.Stock;
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

    /**
     * 재고가 부족한지 여부를 반환합니다.
     * @param quantity 감소시킬 수량
     * @return 재고가 부족한지 여부
     */
    public boolean isOutOfStock(int quantity) {
        return stock.getQuantity() < quantity;
    }

    /**
     * 재고를 감소시킵니다.
     * @param quantity 감소시킬 수량
     */
    public void decreaseStock(int quantity) {
        if (isOutOfStock(quantity)) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        stock.decrease(quantity);
    }   
}
