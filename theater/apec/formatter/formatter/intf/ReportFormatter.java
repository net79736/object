package apec.formatter.formatter.intf;

import apec.formatter.document.report.ReportTable;

/**
 * 보고서 포맷터 인터페이스
 * 
 * <p>ReportTable을 받아서 다양한 형식(Excel, PDF, HTML 등)으로 출력합니다.
 * 도메인 객체(SalesData)에 의존하지 않고, 보고서 전용 모델(ReportTable)만 다룹니다.
 * 
 * <p>이 인터페이스는 다음과 같은 장점을 제공합니다:
 * <ul>
 *   <li>도메인 객체 변경에 영향받지 않음</li>
 *   <li>다양한 도메인 데이터를 동일한 방식으로 처리 가능</li>
 *   <li>Excel 전용 필드, PDF 전용 필드 등을 자유롭게 추가 가능</li>
 * </ul>
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public interface ReportFormatter {
    /**
     * 보고서 테이블을 포맷팅하여 출력합니다.
     * @param table 보고서 테이블 (헤더 + 행 데이터)
     * @param outputPath 출력 경로
     */
    void format(ReportTable table, String outputPath);
}
