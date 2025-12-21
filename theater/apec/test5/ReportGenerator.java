package apec.test5;

import apec.test5.order.Order;

public class ReportGenerator {
    /**
     * 결제 정보를 문자열로 구성합니다.
     * 포인트 부분 결제인 경우 상세 정보를 포함합니다.
     * 
     * @param order 주문
     * @param totalAmount 총 결제 금액
     * @return 결제 정보 문자열
     */
    public static String buildPaymentInfo(Order order, int totalAmount) {
        if (order.hasPointPayment()) {
            int pointAmount = order.getPointAmount();
            int remainingAmount = totalAmount - pointAmount;
            return String.format("최종 결제 금액: %d원 (포인트: %d원, %s: %d원)", 
                    totalAmount, pointAmount, order.getPaymentType(), remainingAmount);
        } else {
            return String.format("최종 결제 금액: %d원 (%s)", totalAmount, order.getPaymentType());
        }
    }
}
