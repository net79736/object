package v0;

import java.time.LocalDateTime;

import common.Money;

public class Screening {
    private Movie movie; // 영화
    private int sequence; // 상영 순서
    private LocalDateTime whenScreened; // 상영 시작 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public Money calculateFee(int audienceCount) {
        return this.movie.calculateMovieFee(this).times(audienceCount);
    }
}
