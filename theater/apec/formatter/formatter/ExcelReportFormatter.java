package apec.formatter.formatter;

import java.util.List;
import apec.formatter.SalesData;
import apec.formatter.document.excel.ExcelWorkbook;
import apec.formatter.document.excel.Sheet;
import apec.formatter.formatter.intf.ReportFormatter;

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