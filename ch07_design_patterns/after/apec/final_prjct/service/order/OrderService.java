package ch07_design_patterns.after.apec.final_prjct.service.order;

import ch07_design_patterns.after.apec.final_prjct.facotry.DiscountPolicyFactory;import ch07_design_patterns.after.apec.final_prjct.repository.custom.CustomerRepository;import ch04_solid_principles.after.apec.order_factory.ProductRepository;

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
