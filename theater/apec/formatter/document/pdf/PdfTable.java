package apec.formatter.document.pdf;

import java.math.BigDecimal;

public class PdfTable {

    /**
     * PDF 테이블에 행을 추가합니다.
     * @param date 날짜
     * @param amount 매출액
     */
    public void addRow(String date, BigDecimal amount) {
        // PDF 테이블에 행을 추가합니다.
        System.out.println("PDF 테이블에 행을 추가합니다. date: " + date + ", amount: " + amount);
    }
    
}
