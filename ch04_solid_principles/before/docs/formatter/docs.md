문제 1-2: 보고서 포맷터의 숨겨진 OCP 위반
```java
public class ReportGenerator {
    public void generateReport(List<SalesData> data, String format) {
        String content = createContent(data);
        
        if (format.equals("PDF")) {
            PdfDocument pdf = new PdfDocument();
            pdf.addContent(content);
            pdf.save("report.pdf");
            
        } else if (format.equals("EXCEL")) {
            ExcelWorkbook excel = new ExcelWorkbook();
            excel.addSheet("Sales", content);
            excel.save("report.xlsx");
            
        } else if (format.equals("HTML")) {
            HtmlDocument html = new HtmlDocument();
            html.setBody(content);
            html.save("report.html");
        }
    }
    
    private String createContent(List<SalesData> data) {
        StringBuilder sb = new StringBuilder();
        for (SalesData sales : data) {
            sb.append(sales.getDate())
              .append(",")
              .append(sales.getAmount())
              .append("\n");
        }
        return sb.toString();
    }
}
```
질문:

이 코드는 문제 1-1보다 "나아 보이는데", 왜 여전히 OCP를 위반하는가?
createContent 메서드의 문제점은 무엇인가?
PDF, Excel, HTML이 각각 다른 형식을 요구한다면 어떻게 될까?