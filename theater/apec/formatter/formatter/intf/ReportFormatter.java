package apec.formatter.formatter.intf;

import java.util.List;
import apec.formatter.SalesData;

public interface ReportFormatter {
    /**
     * 매출 데이터를 포맷팅하여 출력합니다.
     * @param data 매출 데이터
     * @param outputPath 출력 경로
     * @return 포맷팅된 매출 데이터
     */
    void format(List<SalesData> data, String outputPath);
}
