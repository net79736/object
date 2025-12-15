package v0;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; // 영화
    private int sequence; // 상영 순번
    private LocalDateTime whenScreened; // 상영 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    /**
     * 영화 요금을 계산한다.
     * @param audienceCount 인원수
     * @return 영화 요금
     */
    public Money calculateFee(int audienceCount) {
        // 영화 타입에 따라 요금을 계산한다.
        switch (movie.getMovieType()) {
            // 고정 금액 할인 정책을 적용한다.
            case AMOUNT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculateAmountDiscountedFee().times(audienceCount);
                }
                break;
            // 비율 할인 정책을 적용한다.
            case PERCENT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculatePercentDiscountedFee().times(audienceCount);
                }
                break;
            // 미적용 할인 정책을 적용한다.
            case NONE_DISCOUNT:
                return movie.calculateNoneDiscountedFee().times(audienceCount);
        }
        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
}
