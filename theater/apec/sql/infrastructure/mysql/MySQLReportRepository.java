package apec.sql.infrastructure.mysql;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import apec.sql.domain.ReportData;
import apec.sql.infrastructure.ReportRepository;

public class MySQLReportRepository implements ReportRepository {
    @Override
    public List<ReportData> findByMonth(YearMonth month) {
        return new ArrayList<>();
    }
    
}
