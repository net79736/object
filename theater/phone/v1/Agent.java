package phone.v1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Agent {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = LocalDateTime.of(now.getYear(), 12, 25, 23, 57, 0);

        Duration duration = Duration.between(from, now);
        System.out.println(duration.toMinutes());
    }
}
