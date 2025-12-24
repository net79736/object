package apec.formatter.document.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 보고서 테이블 데이터
 * 
 * <p>표 형태의 보고서를 나타내는 모델입니다.
 * 헤더와 행 데이터로 구성되며, 도메인 객체와 완전히 분리되어 있습니다.
 * 
 * <p>이 클래스는 다음과 같은 장점을 제공합니다:
 * <ul>
 *   <li>도메인 객체 변경이 보고서 포맷터에 영향을 주지 않음</li>
 *   <li>Excel 전용 필드, PDF 전용 필드 등을 자유롭게 추가 가능</li>
 *   <li>다양한 도메인 데이터(주문, 환불, 쿠폰 등)를 동일한 구조로 처리 가능</li>
 *   <li>동적 컬럼 구조의 보고서도 처리 가능</li>
 * </ul>
 * 
 * <p>예시:
 * <pre>{@code
 * ReportTable table = new ReportTable();
 * table.setHeaders("날짜", "매출액", "합계");
 * 
 * ReportRow row1 = new ReportRow();
 * row1.addColumns("2025-01-01", new BigDecimal("10000"), new BigDecimal("10000"));
 * table.addRow(row1);
 * }</pre>
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class ReportTable {
    private List<String> headers;
    private List<ReportRow> rows;

    public ReportTable() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    /**
     * 헤더 설정
     * @param headers 헤더 목록
     */
    public void setHeaders(String... headers) {
        this.headers = new ArrayList<>();
        Collections.addAll(this.headers, headers);
    }

    /**
     * 헤더 목록 조회 (불변 리스트)
     * @return 헤더 목록
     */
    public List<String> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    /**
     * 행 추가
     * @param row 보고서 행
     */
    public void addRow(ReportRow row) {
        this.rows.add(row);
    }

    /**
     * 행 목록 조회 (불변 리스트)
     * @return 행 목록
     */
    public List<ReportRow> getRows() {
        return Collections.unmodifiableList(rows);
    }

    /**
     * 행 개수
     * @return 행 개수
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * 헤더 개수
     * @return 헤더 개수
     */
    public int getHeaderCount() {
        return headers.size();
    }

    /**
     * 테이블이 비어있는지 확인
     * @return 비어있으면 true
     */
    public boolean isEmpty() {
        return rows.isEmpty();
    }
}

