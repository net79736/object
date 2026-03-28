package ch06_dependency.after.apec.sql.infrastructure;

import java.time.YearMonth;
import java.util.List;

import ch06_dependency.after.apec.sql.domain.ReportData;
public interface ReportRepository {
    List<ReportData> findByMonth(YearMonth month);
}
