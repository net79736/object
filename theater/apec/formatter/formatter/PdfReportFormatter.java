package apec.formatter.formatter;

import java.util.List;

import apec.formatter.document.pdf.PdfDocument;
import apec.formatter.document.pdf.PdfTable;
import apec.formatter.document.report.ReportRow;
import apec.formatter.document.report.ReportTable;
import apec.formatter.formatter.intf.ReportFormatter;

/**
 * PDF 보고서 포맷터 구현체
 * 
 * <p>ReportTable을 받아서 PDF 파일로 출력합니다.
 * SalesData에 의존하지 않고, 표 형태의 데이터만 처리합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class PdfReportFormatter implements ReportFormatter {

    /**
     * PDF 보고서 포맷팅
     * @param table 보고서 테이블
     * @param outputPath 출력 경로
     */
    @Override
    public void format(ReportTable table, String outputPath) {
        PdfDocument pdf = new PdfDocument();
        pdf.addContent(createPdfTable(table));
        pdf.save(outputPath);
    }

    private PdfTable createPdfTable(ReportTable reportTable) {
        PdfTable pdfTable = new PdfTable();
        
        // 각 행을 PDF 테이블에 추가
        for (ReportRow row : reportTable.getRows()) {
            List<Object> columns = row.getColumns();
            if (columns.size() >= 2) {
                // 첫 번째 컬럼은 날짜(String), 두 번째 컬럼은 금액(Number)
                String date = String.valueOf(columns.get(0));
                Object amountObj = columns.get(1);
                java.math.BigDecimal amount = amountObj instanceof java.math.BigDecimal 
                    ? (java.math.BigDecimal) amountObj
                    : new java.math.BigDecimal(String.valueOf(amountObj));
                
                pdfTable.addRow(date, amount);
            }
        }
        
        return pdfTable;
    }
}
