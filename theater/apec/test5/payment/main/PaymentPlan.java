package apec.test5.payment.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 결제 계획을 나타내는 값 객체
 * 
 * 복합 결제(포인트 + 다른 결제 수단)를 표현합니다.
 * 단일 결제도 하나의 PaymentItem으로 표현할 수 있습니다.
 */
public class PaymentPlan {
    private final List<PaymentItem> paymentItems;

    private PaymentPlan(List<PaymentItem> paymentItems) {
        this.paymentItems = Collections.unmodifiableList(new ArrayList<>(paymentItems));
    }

    /**
     * 단일 결제 계획을 생성합니다.
     * 
     * @param paymentType 결제 수단
     * @param amount 결제 금액
     * @return 결제 계획
     */
    public static PaymentPlan single(PaymentType paymentType, int amount) {
        List<PaymentItem> items = new ArrayList<>();
        items.add(new PaymentItem(paymentType, amount));
        return new PaymentPlan(items);
    }

    /**
     * 복합 결제 계획을 생성합니다.
     * 
     * @param pointAmount 포인트 사용 금액
     * @param remainingPaymentType 나머지 금액 결제 수단
     * @param remainingAmount 나머지 금액
     * @return 결제 계획
     */
    public static PaymentPlan mixed(int pointAmount, PaymentType remainingPaymentType, int remainingAmount) {
        List<PaymentItem> items = new ArrayList<>();
        if (pointAmount > 0) {
            items.add(new PaymentItem(PaymentType.POINT, pointAmount));
        }
        if (remainingAmount > 0) {
            items.add(new PaymentItem(remainingPaymentType, remainingAmount));
        }
        return new PaymentPlan(items);
    }

    public List<PaymentItem> getPaymentItems() {
        return paymentItems;
    }

    /**
     * 결제 항목을 나타내는 값 객체
     */
    public static class PaymentItem {
        private final PaymentType paymentType;
        private final int amount;

        public PaymentItem(PaymentType paymentType, int amount) {
            this.paymentType = paymentType;
            this.amount = amount;
        }

        public PaymentType getPaymentType() {
            return paymentType;
        }

        public int getAmount() {
            return amount;
        }
    }
}

