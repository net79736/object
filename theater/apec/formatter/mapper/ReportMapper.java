package apec.formatter.mapper;

import apec.formatter.document.report.ReportTable;

/**
 * 도메인 객체를 보고서 모델로 변환하는 매퍼 인터페이스
 * 
 * <p>도메인 객체(SalesData, Order 등)를 ReportTable로 변환하는 책임을 가집니다.
 * 이 인터페이스를 통해 도메인과 보고서 표현 계층을 완전히 분리합니다.
 * 
 * <p>예시:
 * <pre>{@code
 * ReportMapper<SalesData> mapper = new SalesDataMapper();
 * ReportTable table = mapper.toReportTable(salesDataList);
 * }</pre>
 * 
 * @param <T> 도메인 객체 타입
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public interface ReportMapper<T> {
    /**
     * 도메인 객체 리스트를 ReportTable로 변환
     * @param data 도메인 객체 리스트
     * @return 보고서 테이블
     */
    ReportTable toReportTable(java.util.List<T> data);
}

