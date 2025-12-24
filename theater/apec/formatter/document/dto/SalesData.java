package apec.formatter.document.dto;

import java.math.BigDecimal;

import common.Money;

/**
 * 매출 데이터
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-24
 */
public class SalesData {
    private String date; // 주문 날짜
    private Money amount; // 매출액

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