package apec.final_prjct.service.condition.dto;

public class ProductSale {
    private Long productId;
    private int saleAmount;

    public ProductSale(Long productId, int saleAmount) {
        this.productId = productId;
        this.saleAmount = saleAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public int getSaleAmount() {
        return saleAmount;
    }
}
