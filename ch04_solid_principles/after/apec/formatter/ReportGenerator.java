package ch04_solid_principles.after.apec.formatter;

import java.util.List;

import ch04_solid_principles.after.apec.formatter.document.report.ReportTable;import ch04_solid_principles.after.apec.formatter.formatter.intf.ReportFormatter;import ch04_solid_principles.after.apec.formatter.mapper.ReportMapper;
/**
 * 보고서 생성 서비스
 * 
 * <p>도메인 객체를 보고서로 변환하는 서비스입니다.
 * Mapper를 통해 도메인 객체를 ReportTable로 변환한 후, Formatter로 출력합니다.
 * 
 * <p>이 구조의 장점:
 * <ul>
 *   <li>도메인 객체와 보고서 표현 계층이 완전히 분리됨</li>
 *   <li>다양한 도메인 데이터를 동일한 방식으로 처리 가능</li>
 *   <li>Excel 전용 필드, PDF 전용 필드 등을 Mapper에서만 처리</li>
 * </ul>
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class ReportGenerator {
    
    /**
     * 보고서 생성 (도메인 객체를 직접 받는 방식)
     * 
     * <p>도메인 객체 리스트를 받아서 Mapper로 변환한 후 Formatter로 출력합니다.
     * 
     * @param <T> 도메인 객체 타입
     * @param data 도메인 객체 리스트 (예: List<SalesData>)
     * @param mapper 도메인 객체를 ReportTable로 변환하는 매퍼
     * @param formatter 보고서 포맷터 (Excel, PDF, HTML)
     * @param outputPath 출력 경로 (예: /Users/jongwook/Desktop/sales.pdf)
     */
    public <T> void generateReport(
            List<T> data, 
            ReportMapper<T> mapper, 
            ReportFormatter formatter, 
            String outputPath) {
        // 도메인 객체를 ReportTable로 변환
        ReportTable table = mapper.toReportTable(data);
        
        // 보고서 생성
        formatter.format(table, outputPath);
    }
    
    /**
     * 보고서 생성 (ReportTable을 직접 받는 방식)
     * 
     * <p>이미 변환된 ReportTable을 받아서 바로 출력합니다.
     * Mapper를 외부에서 처리한 경우 사용합니다.
     * 
     * @param table 보고서 테이블
     * @param formatter 보고서 포맷터 (Excel, PDF, HTML)
     * @param outputPath 출력 경로
     */
    public void generateReport(ReportTable table, ReportFormatter formatter, String outputPath) {
        formatter.format(table, outputPath);
    }
}
