package apec.test10.infrastructure;

import java.time.YearMonth;
import java.util.List;

import apec.test10.domain.ReportData;

public interface ReportRepository {
    List<ReportData> findByMonth(YearMonth month);
}
