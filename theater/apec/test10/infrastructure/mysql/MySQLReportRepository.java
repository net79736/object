package apec.test10.infrastructure.mysql;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import apec.test10.domain.ReportData;
import apec.test10.infrastructure.ReportRepository;

public class MySQLReportRepository implements ReportRepository {
    @Override
    public List<ReportData> findByMonth(YearMonth month) {
        return new ArrayList<>();
    }
    
}
