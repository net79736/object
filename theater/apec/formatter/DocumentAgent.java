package apec.test7;

import java.util.ArrayList;
import java.util.List;
import apec.test7.formatter.ExcelReportFormatter;
import apec.test7.formatter.PdfReportFormatter;
import common.Money;

public class DocumentAgent {
    public static void main(String[] args) {
        List<SalesData> data = new ArrayList<>();
        data.add(new SalesData("2025-01-01", Money.wons(10000)));
        data.add(new SalesData("2025-01-02", Money.wons(20000)));
        data.add(new SalesData("2025-01-03", Money.wons(30000)));

        ReportGenerator generator = new ReportGenerator();

        generator.generateReport(data, new PdfReportFormatter(), "sales.pdf");
        generator.generateReport(data, new ExcelReportFormatter(), "sales.xlsx");
        // 확장에 열려있음: 새 포맷 추가 시 ReportGenerator 수정 불필요
        // generator.generateReport(data, new MarkdownReportFormatter(), "sales.md");  // 새 포맷!
    }
}
