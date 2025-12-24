package apec.formatter.formatter;

import apec.formatter.document.html.HtmlDocument;
import apec.formatter.document.report.ReportRow;
import apec.formatter.document.report.ReportTable;
import apec.formatter.formatter.intf.ReportFormatter;

/**
 * HTML 보고서 포맷터 구현체
 * 
 * <p>ReportTable을 받아서 HTML 파일로 출력합니다.
 * SalesData에 의존하지 않고, 표 형태의 데이터만 처리합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class HtmlReportFormatter implements ReportFormatter {

    @Override
    public void format(ReportTable table, String outputPath) {
        HtmlDocument html = new HtmlDocument();
        
        // HTML 테이블 생성
        StringBuilder htmlTable = new StringBuilder("<table>");
        
        // 헤더 출력
        if (!table.getHeaders().isEmpty()) {
            htmlTable.append("<thead><tr>");
            for (String header : table.getHeaders()) {
                htmlTable.append("<th>").append(escapeHtml(header)).append("</th>");
            }
            htmlTable.append("</tr></thead>");
        }
        
        // 데이터 행 출력
        htmlTable.append("<tbody>");
        for (ReportRow row : table.getRows()) {
            htmlTable.append("<tr>");
            for (Object column : row.getColumns()) {
                htmlTable.append("<td>").append(escapeHtml(String.valueOf(column))).append("</td>");
            }
            htmlTable.append("</tr>");
        }
        htmlTable.append("</tbody>");
        
        htmlTable.append("</table>");
        
        html.setBody(htmlTable.toString());
        html.save(outputPath);
    }

    /**
     * HTML 특수 문자 이스케이프 처리
     * @param text 원본 텍스트
     * @return 이스케이프된 텍스트
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
