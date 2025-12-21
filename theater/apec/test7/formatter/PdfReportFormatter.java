package apec.test7.formatter;

import java.util.List;
import apec.test7.SalesData;
import apec.test7.document.pdf.PdfDocument;
import apec.test7.document.pdf.PdfTable;
import apec.test7.formatter.intf.ReportFormatter;

public class PdfReportFormatter implements ReportFormatter {
    @Override
    public void format(List<SalesData> data, String outputPath) {
        PdfDocument pdf = new PdfDocument();
        pdf.addContent(createPdfTable(data));
        pdf.save(outputPath);
        throw new UnsupportedOperationException("Unimplemented method 'format'");
    }

    private PdfTable createPdfTable(List<SalesData> data) {
        // PDF 테이블 생성 로직
        return new PdfTable();
    }
}
