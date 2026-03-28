package ch05_message_interface.after.claude6_2;

import java.util.List;

public class Order {
    private double amount;

    public double getAmount() {
        return amount;
    }

    /**
     * 주문 리스트의 총 금액을 계산합니다.
     * 
     * @param orders 주문 리스트
     * @return 주문 리스트의 총 금액
     */
    public static double calculateTotalAmount(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return 0;
        }
        return orders.stream().mapToDouble(Order::getAmount).sum();
    }
}
