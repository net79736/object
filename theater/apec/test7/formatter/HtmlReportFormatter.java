package apec.test7.formatter;

import java.util.List;
import apec.test7.SalesData;
import apec.test7.document.html.HtmlDocument;

public class HtmlReportFormatter {
    public void format(List<SalesData> data, String outputPath) {
        HtmlDocument html = new HtmlDocument();
        
        StringBuilder table = new StringBuilder("<table>");
        for (SalesData sales : data) {
            table.append("<tr>")
                 .append("<td>").append(sales.getDate()).append("</td>")
                 .append("<td>").append(sales.getAmount()).append("</td>")
                 .append("</tr>");
        }
        table.append("</table>");
        
        html.setBody(table.toString());
        html.save(outputPath);
    }
}
