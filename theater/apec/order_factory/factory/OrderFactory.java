package apec.order_factory.factory;

import java.util.ArrayList;
import java.util.List;

import apec.order_factory.Customer;
import apec.order_factory.CustomerRepository;
import apec.order_factory.Order;
import apec.order_factory.OrderItem;
import apec.order_factory.Product;
import apec.order_factory.ProductRepository;
import apec.order_factory.policy.intf.DiscountPolicy;

/**
 * 기본 OrderItem 생성 팩토리 구현체
 * 
 * ProductRepository를 사용하여 상품을 조회하고 OrderItem을 생성합니다.
 */
public class OrderFactory {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DiscountPolicyFactory discountPolicyFactory;

    public OrderFactory(CustomerRepository customerRepository, ProductRepository productRepository, DiscountPolicyFactory discountPolicyFactory) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.discountPolicyFactory = discountPolicyFactory;
    }

    public Order createOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId); // 고객 조회
        List<OrderItem> items = createOrderItems(productIds); // 주문 상품 조회
        DiscountPolicy discountPolicy = discountPolicyFactory.create(customer.getGrade()); // 할인 정책 결정
        
        return new Order(customer, items, discountPolicy); // 주문 생성
    }

    public List<OrderItem> createOrderItems(List<Long> productIds) {
        List<OrderItem> items = new ArrayList<>();
        for (Long productId : productIds) {
            Product product = productRepository.findById(productId);
            OrderItem item = new OrderItem(product, 1);  // 기본 수량 1
            items.add(item);
        }
        return items;
    }
}

