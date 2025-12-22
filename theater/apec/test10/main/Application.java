package apec.test10.main;

import java.time.YearMonth;

import apec.test10.domain.Report;
import apec.test10.domain.ReportService;
import apec.test10.infrastructure.ReportRepository;
import apec.test10.infrastructure.mongo.MongoReportRepository;
import apec.test10.infrastructure.mysql.MySQLReportRepository;

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
