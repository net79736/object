package ch04_solid_principles.after.apec.formatter.formatter;

import java.util.List;

import ch04_solid_principles.after.apec.formatter.document.excel.ExcelWorkbook;import ch04_solid_principles.after.apec.formatter.document.excel.Sheet;import ch04_solid_principles.after.apec.formatter.document.report.ReportRow;import ch04_solid_principles.after.apec.formatter.document.report.ReportTable;import ch04_solid_principles.after.apec.formatter.formatter.intf.ReportFormatter;
/**
 * Excel 보고서 포맷터 구현체
 * 
 * <p>ReportTable을 받아서 Excel 파일로 출력합니다.
 * SalesData에 의존하지 않고, 표 형태의 데이터만 처리합니다.
 * 
 * <p>이제 Excel 전용 필드(합계, 수식 등)가 필요하면,
 * Mapper에서 ReportTable에 추가하면 되고, 이 Formatter는 변경할 필요가 없습니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class ExcelReportFormatter implements ReportFormatter {

    @Override
    public void format(ReportTable table, String outputPath) {
        ExcelWorkbook excel = new ExcelWorkbook();
        Sheet sheet = excel.createSheet("Sales");
        
        int row = 0;
        
        // 헤더 출력
        if (!table.getHeaders().isEmpty()) {
            for (int col = 0; col < table.getHeaderCount(); col++) {
                sheet.setCell(row, col, table.getHeaders().get(col));
            }
            row++;
        }
        
        // 데이터 행 출력
        for (ReportRow reportRow : table.getRows()) {
            List<Object> columns = reportRow.getColumns();
            for (int col = 0; col < columns.size(); col++) {
                sheet.setCell(row, col, columns.get(col));
            }
            row++;
        }
        
        excel.save(outputPath);
    }
}