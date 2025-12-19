package test.claude6;

public class OrderService {
    public void processOrder(Order order) {
        Customer customer = order.getCustomer();
        
        // 고객의 등급 확인
        if (customer.getGrade().getLevel() >= 3) {
            // VIP 고객은 배송비 무료
            order.getDelivery().setFee(0);
        }
        
        // 재고 확인
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            if (product.getStock().getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Out of stock");
            }
            product.getStock().decrease(item.getQuantity());
        }
        
        // 결제 처리
        PaymentMethod payment = customer.getPaymentMethods().get(0);
        payment.charge(order.getTotalAmount());
    }
}
