package ch04_solid_principles.after.apec.order_factory;

import ch08_shopping_system.after.apec.payment_order_point.order.Stock;
public class ProductRepository {
    public Product findById(Long id) {
        return new Product(id, "Product Name 1", 10000, new Stock(10));
    }
}
