package apec.test5.order.validator;

import apec.test5.common.User;
import apec.test5.main.PaymentType;
import apec.test5.order.validator.intf.PaymentPlanValidator;

/**
 * 결제 수단 검증
 * 
 * 포인트 부분 결제 시 나머지 금액은 다른 결제 수단을 사용해야 합니다.
 */
public class PaymentTypeValidator implements PaymentPlanValidator {
    @Override
    public void validate(Integer pointAmount, int totalAmount, PaymentType paymentType, User user) {
        if (pointAmount != null && pointAmount > 0 && paymentType == PaymentType.POINT) {
            throw new RuntimeException("포인트 부분 결제 시 나머지 금액은 다른 결제 수단을 사용해야 합니다.");
        }
    }
}

