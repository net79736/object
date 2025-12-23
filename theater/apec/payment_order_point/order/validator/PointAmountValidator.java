package apec.payment_order_point.order.validator;

import apec.payment_order_point.common.User;
import apec.payment_order_point.main.PaymentType;
import apec.payment_order_point.order.validator.intf.PaymentPlanValidator;

/**
 * 포인트 사용 금액 검증
 * 
 * 포인트 사용 금액이 총 금액보다 큰지 검증합니다.
 */
public class PointAmountValidator implements PaymentPlanValidator {
    @Override
    public void validate(Integer pointAmount, int totalAmount, PaymentType paymentType, User user) {
        if (pointAmount != null && pointAmount > totalAmount) {
            throw new RuntimeException("포인트 사용 금액이 총 금액보다 큽니다. 포인트: " + pointAmount + ", 총액: " + totalAmount);
        }
    }
}

