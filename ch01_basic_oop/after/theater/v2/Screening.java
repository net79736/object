package ch01_basic_oop.after.theater.v2;

import java.time.LocalDateTime;

import common.Money;

public class Screening {
    private Movie movie;
    private int sequence; // 상영순서
    private LocalDateTime whenScreened; // 상영시작시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    /**
     * 상영 순서 확인
     * @param sequence 상영 순서
     * @return 상영 순서 일치 여부
     */
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    /**
     * 예매 생성
     * @param customer 관객
     * @param audienceCount 관객 수
     * @return 예매 정보
     */
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(
            customer, // 관객
            this, // 상영 정보
            calculateFee(audienceCount), // 요금
            audienceCount // 관객 수
        );
    }

    /**
     * 요금 계산
     * @param audienceCount 관객 수
     * @return 요금
     */
    public Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this) // 영화 요금 계산
                    .times(audienceCount); // 관객 수 만큼 요금 계산
    }
}
