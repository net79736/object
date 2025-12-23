package apec.order_factory;

public class OrderItem {
    private Product item; // 주문 상품
    private int quantity; // 주문 수량

    public OrderItem(Product item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    /**
     * 주문 상품을 반환합니다.
     * getItem()과 동일하지만 더 명확한 네이밍을 제공합니다.
     * 
     * @return 주문 상품
     */
    public Product getProduct() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reserveStock() {
        item.decreaseStock(quantity);
    }

}
