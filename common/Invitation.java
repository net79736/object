package common;

import java.time.LocalDateTime;
import java.util.UUID;

public class Invitation {
    private UUID id;
    private LocalDateTime whenIssued;

    public Invitation(UUID id, LocalDateTime whenIssued) {
        this.id = id;
        this.whenIssued = whenIssued;
    }

    public UUID getId() {
        return id;
    }
}
