package ch01_basic_oop.after.theater.v1;

import common.Money;

public class Reservation {
    private Customer customer; // 관객
    private Screening screening; // 상영
    private Money money; // 요금
    private int audienceCount; // 관객수
    
    public Reservation(Customer customer, Screening screening, Money money, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.money = money;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount) {
        this.audienceCount = audienceCount;
    }
}
