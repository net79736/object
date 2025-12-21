package apec.test2;

import java.util.List;
import apec.test1.Order;
import common.Money;

/**
 * 매출 통계를 나타내는 값 객체(Value Object)입니다.
 * 
 * 여러 주문에 대한 집계 정보(총 매출, 주문 건수, 평균 주문액)를 담고 있으며,
 * 주문 목록으로부터 통계를 생성하는 팩토리 메서드를 제공합니다.
 * 
 * 불변 객체로 설계되어 부작용을 방지합니다.
 */
public class SalesStatistics {
    private final Money totalSales;
    private final int totalOrders;
    private final Money averageOrderValue;

    private SalesStatistics(Money totalSales, int totalOrders, Money averageOrderValue) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
        this.averageOrderValue = averageOrderValue;
    }

    /**
     * 주문 목록으로부터 매출 통계를 생성합니다.
     * 
     * @param orders 주문 목록
     * @return 매출 통계 객체
     */
    public static SalesStatistics from(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new SalesStatistics(Money.ZERO, 0, Money.ZERO);
        }

        Money totalSales = calculateTotalSales(orders);
        int totalOrders = orders.size();
        Money averageOrderValue = calculateAverageOrderValue(totalSales, totalOrders);

        return new SalesStatistics(totalSales, totalOrders, averageOrderValue);
    }

    /**
     * 주문 목록의 총 매출을 계산합니다.
     * 
     * @param orders 주문 목록
     * @return 총 매출
     */
    private static Money calculateTotalSales(List<Order> orders) {
        Money total = Money.ZERO;
        for (Order order : orders) {
            total = total.plus(order.getAmount());
        }
        return total;
    }

    /**
     * 평균 주문액을 계산합니다.
     * 
     * @param totalSales 총 매출
     * @param totalOrders 주문 건수
     * @return 평균 주문액 (주문 건수가 0이면 0원 반환)
     */
    private static Money calculateAverageOrderValue(Money totalSales, int totalOrders) {
        if (totalOrders == 0) {
            return Money.ZERO;
        }
        return new Money(totalSales.getAmount().divide(
            java.math.BigDecimal.valueOf(totalOrders), 
            2, 
            java.math.RoundingMode.HALF_UP
        ));
    }

    public Money getTotalSales() {
        return totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public Money getAverageOrderValue() {
        return averageOrderValue;
    }
}

