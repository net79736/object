package apec.test8;

import apec.test5.order.Stock;

public class ProductRepository {
    public Product findById(Long id) {
        return new Product(id, "Product Name 1", 10000, new Stock(10));
    }
}
