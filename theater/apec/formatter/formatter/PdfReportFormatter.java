package apec.formatter.formatter;

import java.util.List;
import apec.formatter.SalesData;
import apec.formatter.document.pdf.PdfDocument;
import apec.formatter.document.pdf.PdfTable;
import apec.formatter.formatter.intf.ReportFormatter;

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
