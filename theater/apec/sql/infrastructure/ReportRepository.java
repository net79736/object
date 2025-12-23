package apec.sql.infrastructure;

import java.time.YearMonth;
import java.util.List;

import apec.sql.domain.ReportData;

public interface ReportRepository {
    List<ReportData> findByMonth(YearMonth month);
}
