package apec.formatter;

import java.util.ArrayList;
import java.util.List;

import apec.formatter.document.dto.SalesData;
import apec.formatter.formatter.ExcelReportFormatter;
import apec.formatter.formatter.PdfReportFormatter;
import apec.formatter.mapper.SalesDataMapper;
import common.Money;

/**
 * 문서 생성 에이전트 (메인 클래스)
 * 
 * <p>개선된 구조의 사용 예시를 보여줍니다.
 * 
 * <p>변경 사항:
 * <ul>
 *   <li>SalesData → SalesDataMapper → ReportTable → Formatter</li>
 *   <li>Formatter가 도메인 객체에 의존하지 않음</li>
 *   <li>다른 도메인 데이터도 동일한 방식으로 처리 가능</li>
 * </ul>
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class DocumentAgent {
    public static void main(String[] args) {
        // 도메인 데이터 준비
        List<SalesData> data = new ArrayList<>();
        data.add(new SalesData("2025-01-01", Money.wons(10000)));
        data.add(new SalesData("2025-01-02", Money.wons(20000)));
        data.add(new SalesData("2025-01-03", Money.wons(30000)));

        // 보고서 생성기와 매퍼 준비
        ReportGenerator generator = new ReportGenerator();
        SalesDataMapper mapper = new SalesDataMapper();

        // PDF 보고서 생성
        generator.generateReport(data, mapper, new PdfReportFormatter(), "sales.pdf");
        
        // Excel 보고서 생성
        generator.generateReport(data, mapper, new ExcelReportFormatter(), "sales.xlsx");
        
        // 확장에 열려있음:
        // 1. 새 포맷 추가 시 ReportGenerator 수정 불필요
        // 2. 다른 도메인 데이터(Order, Refund 등)도 동일한 방식으로 처리 가능
        // 3. Excel 전용 필드가 필요하면 SalesDataMapper만 수정하면 됨
    }
}
