package apec.stock_delivery;

public class OrderItem {
    private Product product; // 주문 상품
    private int quantity; // 주문 수량

    /**
     * 주문 상품을 생성합니다.
     * @param product 주문 상품
     * @param quantity 주문 수량
     */
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 주문 상품의 재고를 감소시킵니다.
     * @param quantity 감소시킬 수량
     */
    public void decreaseProductStock() {
        product.decreaseStock(quantity);
    }
}
