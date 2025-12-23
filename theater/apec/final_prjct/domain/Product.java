package apec.final_prjct.domain;

import apec.final_prjct.common.Stock;

/**
 * 상품 클래스
 * 상품 정보를 나타내는 클래스
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
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
    
    
    
}
