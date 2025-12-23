package apec.test7.formatter;

import java.util.List;
import apec.test7.SalesData;
import apec.test7.document.excel.ExcelWorkbook;
import apec.test7.document.excel.Sheet;
import apec.test7.formatter.intf.ReportFormatter;

public class ExcelReportFormatter implements ReportFormatter {
    public void format(List<SalesData> data, String outputPath) {
        ExcelWorkbook excel = new ExcelWorkbook();
        Sheet sheet = excel.createSheet("Sales");
        
        int row = 0;
        for (SalesData sales : data) {
            sheet.setCell(row, 0, sales.getDate());
            sheet.setCell(row, 1, sales.getAmount());
            row++;
        }
        
        excel.save(outputPath);
    }
}