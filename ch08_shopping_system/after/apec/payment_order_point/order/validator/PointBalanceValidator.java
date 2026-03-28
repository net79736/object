package ch08_shopping_system.after.apec.payment_order_point.order.validator;

import ch08_shopping_system.after.apec.payment_order_point.common.User;import ch08_shopping_system.after.apec.payment_order_point.main.PaymentType;import ch08_shopping_system.after.apec.payment_order_point.order.validator.intf.PaymentPlanValidator;
/**
 * 포인트 잔액 검증
 * 
 * 포인트 사용 금액이 사용자의 포인트 잔액보다 큰지 검증합니다.
 */
public class PointBalanceValidator implements PaymentPlanValidator {
    @Override
    public void validate(Integer pointAmount, int totalAmount, PaymentType paymentType, User user) {
        if (pointAmount != null && pointAmount > 0) {
            user.checkPointBalance(pointAmount);
        }
    }
}

