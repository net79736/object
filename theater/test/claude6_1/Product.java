package test.claude6_1;

public class Product {

    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public boolean checkOutOfStock(int quantity) {
        return stock.getQuantity() < quantity;
    }

    // Product.java에 추가할 메서드
    /**
     * 재고를 감소시킵니다.
     * 재고가 부족하면 예외를 발생시킵니다.
     * 
     * @param quantity 감소시킬 수량
     * @throws RuntimeException 재고가 부족한 경우
     */
    public void decreaseStock(int quantity) {
        if (checkOutOfStock(quantity)) {
            throw new RuntimeException("Out of stock");
        }
        stock.decrease(quantity);
    }

    public boolean isOutOfStock(int quantity) {
        return checkOutOfStock(quantity);
    }
}
