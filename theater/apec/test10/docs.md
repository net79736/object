문제 3-1: 의존성 방향의 역전
```java
// 현재 구조
public class ReportService {
    private MySQLReportRepository repository;
    
    public ReportService() {
        this.repository = new MySQLReportRepository();
    }
    
    public Report generateMonthlyReport(YearMonth month) {
        List<ReportData> data = repository.findByMonth(month);
        return Report.from(data);
    }
}

public class MySQLReportRepository {
    private DataSource dataSource;
    
    public List<ReportData> findByMonth(YearMonth month) {
        // MySQL 쿼리 실행
        String sql = "SELECT * FROM reports WHERE month = ?";
        // ...
    }
}

// 새 요구사항: MongoDB로 전환하고 싶음
public class MongoReportRepository {
    private MongoClient mongoClient;
    
    public List<ReportData> findByMonth(YearMonth month) {
        // MongoDB 쿼리 실행
        // ...
    }
}
```

현재 구조에서 MySQL을 MongoDB로 교체할 때의 문제점을 설명하시오
"상위 수준 모듈이 하위 수준 모듈에 의존"하는 것이 왜 문제인가?
DIP를 적용하여 리팩토링하고, 인터페이스의 "소유권"에 대해 설명하시오