package test.claude6_2;

import java.sql.Date;
import java.util.List;


public class ReportGenerator {
    public String generateSalesReport(Date startDate, Date endDate) {
        // OrderRepository를 사용하여 주문 목록을 조회합니다.
        OrderRepository orderRepository = new OrderRepository();
        // 주문 목록을 조회합니다.
        List<Order> orders = orderRepository.findByDateRange(startDate, endDate);
        
        // 주문 목록의 총 매출을 계산합니다.
        double totalSales = Order.calculateTotalAmount(orders);
        int totalOrders = orders.size();
        
        // 평균 주문액 계산 (0으로 나누기 방지)
        double averageOrderValue = totalOrders > 0 ? totalSales / totalOrders : 0;
        
        return formatReport(totalSales, totalOrders, averageOrderValue);
    }
    
    /**
     * 보고서를 포맷팅합니다.
     * 
     * @param totalSales 총 매출
     * @param totalOrders 주문 건수
     * @param averageOrderValue 평균 주문액
     * @return 포맷팅된 보고서 문자열
     */
    private String formatReport(double totalSales, int totalOrders, double averageOrderValue) {
        return String.format("총 매출: %.2f원\n주문 건수: %d건\n평균 주문액: %.2f원", 
                           totalSales, totalOrders, averageOrderValue);
    }
}
