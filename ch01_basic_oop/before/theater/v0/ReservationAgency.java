package ch01_basic_oop.before.theater.v0;

import common.Money;

/**
 * ReservationAgency 가 모든 데이터 객체에 의존
 *   - DiscountCondition의 데이터가 변경 → ReservationAgency도 함께 수정
 *   - Screening의 데이터가 변경 → ReservationAgency도 함께 수정
 *   - 새로운 할인 정책을 추가 → switch 구문에 새로운 case 절을 추가
 */
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();
        boolean discountable = false;
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                // 기간 할인 조건 체크
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else {
                // 순서 할인 조건 체크
                discountable = condition.getSequence() == screening.getSequence();
            }

            // 할일 조건을 만족하는 경우 루프 종료
            if (discountable) {
                break;
            }
        }

        Money fee;
        
        if (discountable) {
            // 할인 가능한 경우
            Money discountAmount = Money.ZERO; // 할인 요금
            // 타입 별 할인 요금 계산
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
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
