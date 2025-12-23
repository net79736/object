package apec.final_prjct.domain;

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

    public int getQuantity() {
        return quantity;
    }
}
