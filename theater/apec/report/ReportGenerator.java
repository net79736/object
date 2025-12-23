package apec.report;

import java.util.Date;
import java.util.List;

import apec.stock_delivery.Order;

/**
 * 매출 보고서를 생성하는 애플리케이션 서비스입니다.
 * 
 * 도메인 로직(SalesStatistics)과 프레젠테이션 로직(포맷팅)을 분리하여
 * 단일 책임 원칙을 준수합니다.
 */
public class ReportGenerator {
    public String generateSalesReport(Date startDate, Date endDate) {
        OrderRepository orderRepository = new OrderRepository();
        List<Order> orders = orderRepository.findByDateRange(startDate, endDate);
        
        // 도메인 객체를 통해 매출 통계를 계산합니다.
        SalesCaculator statistics = SalesCaculator.from(orders);
        
        // 보고서를 포맷팅합니다.
        return formatReport(statistics);
    }

    /**
     * 매출 통계를 보고서 형식의 문자열로 포맷팅합니다.
     * 
     * @param statistics 매출 통계
     * @return 포맷팅된 보고서 문자열
     */
    private String formatReport(SalesCaculator statistics) {
        return String.format("총 매출: %.2f원\n주문 건수: %d건\n평균 주문액: %.2f원", 
                           statistics.getTotalSales().getAmount().doubleValue(), // 총 매출
                           statistics.getTotalOrders(), // 주문 건수
                           statistics.getAverageOrderValue().getAmount().doubleValue() // 평균 주문액
        );
    }
}
