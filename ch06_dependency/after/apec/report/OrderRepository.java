package ch06_dependency.after.apec.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ch04_solid_principles.after.apec.stock_delivery.Order;
public class OrderRepository {

    public List<Order> findByDateRange(Date startDate, Date endDate) {
        return new ArrayList<>();
    }
    
}
