package apec.test6;

public enum OrderStatus {
    PENDING, // 주문 생성 후 결제 전
    PAID, // 결제 완료
    FAILED, // 결제 실패
    CANCELLED; // 주문 취소

    public boolean isPending() {
        return this == PENDING;
    }

    public boolean isPaid() {
        return this == PAID;
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }

    public boolean isFailed() {
        return this == FAILED;
    }
}
