package v0;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; // 영화
    private int sequence; // 상영 순번
    private LocalDateTime whenScreened; // 상영 시간

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public void setWhenScreened(LocalDateTime whenScreened) {
        this.whenScreened = whenScreened;
    }
}
