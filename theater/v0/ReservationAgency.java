package v0;

import v0.condition.DiscountCondition;
import v0.condition.DiscountConditionType;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie(); // 영화 정보
        boolean discountable = false;

        // 루프를 돌면서 할인 조건을 확인한다.
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                // 기간 조건을 확인한다.
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else {
                // 순번 조건을 확인한다.
                discountable = condition.getSequence() == screening.getSequence();
            }

            // 할인 조건을 만족하면 반복문을 종료한다.
            if (discountable) {
                break;
            }
        }

        Money fee;

        // 할인 조건을 만족하면 할인 금액을 계산한다.
        if (discountable) {
            // 할인 금액을 계산한다.
            Money discountAmount = Money.ZERO;
            // 할인 타입에 따라 할인 금액을 계산한다.
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    // 고정 금액 할인 정책을 적용한다.
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    // 비율 할인 정책을 적용한다.
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }

            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee, audienceCount);
    }
}
