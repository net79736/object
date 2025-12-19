package test.claude6_1.main;

import test.claude6_1.Order;

public class OrderService {
    public void processOrder(Order order) {     
        // 배송비 계산 (고객 등급에 따라 자동 계산)
        order.calculateDeliveryFee();
        
        // 재고 확인
        order.decreaseStock();
        
        // 결제 처리 (Customer가 자신의 결제수단을 선택하고 결제 처리)
        order.processPayment();
    }
}
