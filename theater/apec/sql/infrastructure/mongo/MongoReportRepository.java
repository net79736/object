package apec.sql.infrastructure.mongo;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import apec.sql.domain.ReportData;
import apec.sql.infrastructure.ReportRepository;

public class MongoReportRepository implements ReportRepository {
    @Override
    public List<ReportData> findByMonth(YearMonth month) {
        return new ArrayList<>();
    }
    
}
