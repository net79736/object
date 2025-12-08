package v4;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; // 영화
    private int sequence; // 상영 순번
    private LocalDateTime whenScreened; // 상영시작시간 ()

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }


    public Money getMovieFee() {
        return movie.getFee();
    }

    /**
     * 영화 예매를 처리한다.
     * 
     * @param customer 고객 객체
     * @param audienceCount 인원수
     * @return
     */
    public Reservation reserve(Customer customer, int audienceCount) {
        // customer 고객, screening 상영, fee 요금, audienceCount 인원 수
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }
    
    /**
     * 영화 요금을 계산한다.
     * 
     * @param audienceCount 인원수
     * @return
     */
    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
