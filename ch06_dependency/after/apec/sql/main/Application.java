package ch06_dependency.after.apec.sql.main;

import java.time.YearMonth;

import ch06_dependency.after.apec.sql.domain.Report;import ch06_dependency.after.apec.sql.domain.ReportService;import ch06_dependency.after.apec.sql.infrastructure.ReportRepository;import ch06_dependency.after.apec.sql.infrastructure.mongo.MongoReportRepository;import ch06_dependency.after.apec.sql.infrastructure.mysql.MySQLReportRepository;
public class Application {
    public static void main(String[] args) {
        // 의존성 조립은 가장 바깥쪽에서
        ReportRepository repository = createRepository();
        ReportService service = new ReportService(repository);
        
        // 사용
        Report report = service.generateMonthlyReport(YearMonth.now());
    }

    private static ReportRepository createRepository() {
        String dbType = System.getProperty("db.type");
        
        return switch (dbType) {
            case "mysql" -> new MySQLReportRepository();
            case "mongo" -> new MongoReportRepository();
            // case "memory" -> new InMemoryReportRepository();  // 테스트용
            default -> throw new IllegalArgumentException("Unknown DB type");
        };
    }
}
