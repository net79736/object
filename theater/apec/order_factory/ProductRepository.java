package apec.order_factory;

import apec.payment_order_point.order.Stock;

public class ProductRepository {
    public Product findById(Long id) {
        return new Product(id, "Product Name 1", 10000, new Stock(10));
    }
}
