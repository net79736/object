package apec.final_prjct.service.order;

import apec.final_prjct.facotry.DiscountPolicyFactory;
import apec.final_prjct.repository.custom.CustomerRepository;
import apec.order_factory.ProductRepository;


public class OrderService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DiscountPolicyFactory discountPolicyFactory;

    public OrderService(CustomerRepository customerRepository, ProductRepository productRepository, DiscountPolicyFactory discountPolicyFactory) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.discountPolicyFactory = discountPolicyFactory;
    }
}
