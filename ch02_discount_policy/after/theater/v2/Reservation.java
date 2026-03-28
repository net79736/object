package ch02_discount_policy.after.theater.v2;

import common.Money;

public class Reservation {
    private Customer customer; // 관객
    private Screening screening; // 상영 정보
    private Money fee; // 요금
    private int audienceCount; // 관객 수

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
