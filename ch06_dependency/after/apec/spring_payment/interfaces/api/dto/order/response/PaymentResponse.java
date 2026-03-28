package ch06_dependency.after.apec.spring_payment.interfaces.api.dto.order.response;

public class PaymentResponse {
    private boolean success;

    public PaymentResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

