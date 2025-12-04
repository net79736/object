package v1;

import java.time.LocalDateTime;

/**
 * 초대 시간을 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Invitation {
    private LocalDateTime when;

    /**
     * 생성자로 초기화된 초대 시간을 반환한다.
     * @param when 초대 시간
     */
    public Invitation(LocalDateTime when) {
        this.when = when;
    }

    /**
     * 초대 시간을 반환한다.
     * @return 초대 시간
     */
    public LocalDateTime getWhen() {
        return when;
    }
}