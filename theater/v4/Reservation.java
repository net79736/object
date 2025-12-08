package v4;

public class Reservation {
    private Customer customer; // 고객
    private Screening screening; // 상영
    private Money fee; // 요금
    private int audienceCount; // 인원 수

    /**
     * 
     * @param customer 고객
     * @param screening 상영
     * @param fee 요금
     * @param audienceCount 인원 수
     */
    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
