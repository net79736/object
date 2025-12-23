package apec.test7;

import java.util.List;
import apec.test7.formatter.intf.ReportFormatter;

public class ReportGenerator {
    public void generateReport(List<SalesData> data, ReportFormatter formatter, String outputPath) {
        formatter.format(data, outputPath);
    }
}
