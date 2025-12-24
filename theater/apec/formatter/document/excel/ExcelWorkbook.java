package apec.formatter.document.excel;

public class ExcelWorkbook {
    /**
     * Excel 파일을 저장합니다.
     * @param outputPath 출력 경로
     */
    public void save(String outputPath) {
        // Excel 파일을 저장합니다.
    }

    // 시트 생성
    public Sheet createSheet(String sheetName) {
        return new Sheet();
    }
}
