package apec.formatter;

import java.util.List;
import apec.formatter.formatter.intf.ReportFormatter;

public class ReportGenerator {
    public void generateReport(List<SalesData> data, ReportFormatter formatter, String outputPath) {
        formatter.format(data, outputPath);
    }
}
