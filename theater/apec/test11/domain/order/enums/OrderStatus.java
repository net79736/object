package apec.test11.domain.order.enums;

public enum OrderStatus {
    PENDING,
    PAID,
    FAILED,
    CANCELLED;

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

