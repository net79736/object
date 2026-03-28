package ch07_design_patterns.after.apec.final_prjct.repository.condition;

import java.time.DayOfWeek;
import java.time.LocalTime;

import ch07_design_patterns.after.apec.final_prjct.domain.Order;import ch07_design_patterns.after.apec.final_prjct.repository.condition.intf.DiscountCondition;
/**
 * 기간 할인 조건
 * 특정 요일과 시간 범위에 따른 할인 조건을 검증합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class PeriodDiscountCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodDiscountCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return false;
    }
}
