package v99;

import java.time.DayOfWeek;
import java.time.LocalTime;

import v0.Screening;

/**
 * 기간 할인 조건
 */
public class PeriodDiscountCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek; // 기간 할인 상영 요일 (예: 월요일, 화요일, 수요일, 목요일, 금요일, 토요일, 일요일)
    private LocalTime startTime; // 기간 할인 상영 시작 시간 (예: 10:00, 11:00 ...)
    private LocalTime endTime; // 기간 할인 상영 종료 시간 (예: 10:00, 11:00 ...)

    public PeriodDiscountCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 할인 조건 검증
     * 특정 요일과 시간 범위에 따른 할인 조건을 검증
     * 
     * @param screening 상영 정보 (상영 시간 정보)
     * @return 할인 가능 여부 (true: 할인 가능, false: 할인 불가능)
     */
    @Override
    public boolean isDiscountable(Screening screening) {
        // from 1 (Monday) to 7 (Sunday)
        DayOfWeek screeningDayOfWeek = screening.getWhenScreened().getDayOfWeek(); // 상영 요일 (예: 월요일, 화요일, 수요일, 목요일, 금요일, 토요일, 일요일) 
        LocalTime screeningTime = screening.getWhenScreened().toLocalTime(); // 상영 시간 (예: 10:00, 11:00 ...)

        boolean isSameDay = screeningDayOfWeek.equals(dayOfWeek); // 상영 요일과 기간 할인 상영 요일이 같은지 확인
        boolean isAfterOrAtStart = startTime.compareTo(screeningTime) <= 0; // 상영 시간이 기간 할인 상영 시작 시간 이후 또는 같은지 확인
        boolean isBeforeOrAtEnd = endTime.compareTo(screeningTime) >= 0; // 상영 시간이 기간 할인 상영 종료 시간 이전 또는 같은지 확인

        return isSameDay && isAfterOrAtStart && isBeforeOrAtEnd; // 상영 요일과 시간이 기간 할인 조건을 만족하는지 확인
    }
}
