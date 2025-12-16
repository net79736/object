package v2.condition;

import java.time.DayOfWeek;
import java.time.LocalTime;

import v2.Screening;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek; // 기간 할인 상영 요일 (예: 월요일, 화요일, 수요일, 목요일, 금요일, 토요일, 일요일)
    private LocalTime startTime; // 기간 할인 상영 시작 시간 (예: 10:00, 11:00 ...)
    private LocalTime endTime; // 기간 할인 상영 종료 시간 (예: 10:00, 11:00 ...)

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        DayOfWeek screeningDayOfWeek = screening.getWhenScreened().getDayOfWeek(); // 기간 할인 상영 요일 (예: 월요일, 화요일, 수요일, 목요일, 금요일, 토요일, 일요일)
        LocalTime screeningTime = screening.getWhenScreened().toLocalTime(); // 상영 시간 (예: 10:00, 11:00 ...)

        boolean isSameDay = screeningDayOfWeek.equals(dayOfWeek); // 할인 가능 요일인지 확인
        boolean isAfterOrAtStart = startTime.compareTo(screeningTime) <= 0; // 할인 가능 [시작 시간] 인지 확인
        boolean isBeforeOrAtEnd = endTime.compareTo(screeningTime) >= 0; // 할인 가능 [종료 시간] 인지 확인

        return isSameDay && isAfterOrAtStart && isBeforeOrAtEnd;
    }
}
