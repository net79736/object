package apec.formatter.document.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 보고서 행 데이터
 * 
 * <p>표 형태의 보고서에서 한 행을 나타냅니다.
 * 도메인 객체와 독립적으로 보고서 출력에 필요한 데이터만 담습니다.
 * 
 * <p>예시:
 * <pre>{@code
 * ReportRow row = new ReportRow();
 * row.addColumn("2025-01-01");
 * row.addColumn(new BigDecimal("10000"));
 * }</pre>
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class ReportRow {
    private final List<Object> columns;

    public ReportRow() {
        this.columns = new ArrayList<>();
    }

    /**
     * 컬럼 추가
     * @param value 컬럼 값 (String, Number, Date 등)
     */
    public void addColumn(Object value) {
        this.columns.add(value);
    }

    /**
     * 모든 컬럼을 한 번에 추가
     * @param values 컬럼 값들
     */
    public void addColumns(Object... values) {
        Collections.addAll(this.columns, values);
    }

    /**
     * 컬럼 목록 조회 (불변 리스트)
     * @return 컬럼 목록
     */
    public List<Object> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * 특정 인덱스의 컬럼 값 조회
     * @param index 컬럼 인덱스
     * @return 컬럼 값
     */
    public Object getColumn(int index) {
        if (index < 0 || index >= columns.size()) {
            throw new IndexOutOfBoundsException("Column index out of range: " + index);
        }
        return columns.get(index);
    }

    /**
     * 컬럼 개수
     * @return 컬럼 개수
     */
    public int getColumnCount() {
        return columns.size();
    }
}

