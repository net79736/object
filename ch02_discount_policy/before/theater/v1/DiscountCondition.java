package ch02_discount_policy.before.theater.v1;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private DiscountConditionType type; // SEQUENCE, PERIOD
    
    private int sequence; // 상영 순서
    
    private DayOfWeek dayOfWeek; // 상영 요일
    private LocalTime startTime; // 시작 시간
    private LocalTime endTime; // 종료 시간

    public DiscountConditionType getType() {
        return type;
    }

    /**
     * 기간 할인 조건 검증
     * 특정 요일과 시간 범위에 따른 할인 조건을 검증
     * 
     * @param dayOfWeek 요일
     * @param time 상영 시작 시간
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if (type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.compareTo(time)<= 0 &&
                this.endTime.compareTo(time) >= 0;
    }

    /**
     * 순서 할인 조건 검증
     * 특정 상영 순서에 따른 할인 조건을 검증
     * 
     * @param sequence 상영 순서
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(int sequence) {
        if (type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }
        
        return this.sequence == sequence;
    }
}
