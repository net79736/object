package ch04_solid_principles.after.apec.formatter.mapper;

import java.util.List;

import ch04_solid_principles.after.apec.formatter.document.dto.SalesData;import ch04_solid_principles.after.apec.formatter.document.report.ReportRow;import ch04_solid_principles.after.apec.formatter.document.report.ReportTable;
/**
 * SalesData를 ReportTable로 변환하는 매퍼
 * 
 * <p>SalesData 도메인 객체를 보고서 출력용 ReportTable로 변환합니다.
 * 이 매퍼를 통해 SalesData의 변경이 Formatter에 영향을 주지 않습니다.
 * 
 * <p>만약 Excel 전용 필드(합계, 누적합 등)가 필요하다면,
 * 이 매퍼에서만 처리하면 되고, SalesData 도메인 객체는 변경할 필요가 없습니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class SalesDataMapper implements ReportMapper<SalesData> {

    @Override
    public ReportTable toReportTable(List<SalesData> data) {
        ReportTable table = new ReportTable();
        
        // 헤더 설정
        table.setHeaders("날짜", "매출액");
        
        // 데이터 행 추가
        for (SalesData sales : data) {
            ReportRow row = new ReportRow();
            row.addColumns(
                sales.getDate(),
                sales.getAmount()
            );
            table.addRow(row);
        }
        
        return table;
    }
}

