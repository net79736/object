package apec.sql.domain;

import java.time.YearMonth;
import java.util.List;

import apec.sql.infrastructure.ReportRepository;

public class ReportService {
    private ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report generateMonthlyReport(YearMonth month) {
        List<ReportData> data = repository.findByMonth(month);
        return Report.from(data);
    }
}
