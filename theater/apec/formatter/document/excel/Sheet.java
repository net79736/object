package apec.formatter.document.excel;

import java.math.BigDecimal;

public class Sheet {
    /**
     * Excel 시트에 셀을 설정합니다.
     * @param row 행
     * @param column 열
     * @param value 값
     */
    public void setCell(int row, int column, String value) {
        // Excel 시트에 셀을 설정합니다.
    }

    /**
     * Excel 시트에 셀을 설정합니다.
     * @param row 행
     * @param column 열
     * @param value 값
     */
    public void setCell(int row, int column, BigDecimal value) {
        // Excel 시트에 셀을 설정합니다.
    }

    /**
     * Excel 시트에 셀을 설정합니다. (Object 타입 지원)
     * @param row 행
     * @param column 열
     * @param value 값 (String, Number, Date 등)
     */
    public void setCell(int row, int column, Object value) {
        // Excel 시트에 셀을 설정합니다.
        // 실제 구현에서는 타입에 따라 적절한 형식으로 변환
        if (value instanceof String) {
            setCell(row, column, (String) value);
        } else if (value instanceof BigDecimal) {
            setCell(row, column, (BigDecimal) value);
        } else if (value instanceof Number) {
            setCell(row, column, BigDecimal.valueOf(((Number) value).doubleValue()));
        } else {
            setCell(row, column, String.valueOf(value));
        }
    }
}
