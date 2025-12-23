package apec.formatter;

import java.math.BigDecimal;
import common.Money;

public class SalesData {
    private String date;
    private Money amount;

    public SalesData(String date, Money amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount.getAmount();
    }
}
