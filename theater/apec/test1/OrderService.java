package apec.test1;

public class OrderService {
    public void processOrder(Order order) {
        Customer customer = order.getCustomer();

        // 배송비 계산
        order.calculateDeliveryFee();

        // 재고 감소
        order.reverseStock();

        // 결제 처리
        customer.pay(order.getTotalAmount());
    }    
}
