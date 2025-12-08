package v4.condition;

import java.time.DayOfWeek;
import java.time.LocalTime;

import v4.Screening;

/**
 * 상영 요일과 시간에 따른 할인 조건을 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-04
 */
public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek; // 상영 요일
    private LocalTime startTime; // 상영 시작시간
    private LocalTime endTime; // 상영 종료시간

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * 상영 시간에 따른 할인 조건을 확인한다.
     * @param screening 상영 정보
     * @return
     */
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) && // 상영 요일이 일치하고
            startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 && // "상영 시작시간"이 시작시간보다 크거나 같고
            endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0; // "상영 시작시간"이 종료시간보다 작거나 같으면 true를 반환한다.
    }
}
