package apec.test5.order;

public class OrderItem {
    private Product product; // 주문 상품
    private int quantity; // 주문 수량

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
