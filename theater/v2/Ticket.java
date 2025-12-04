package v2;

/**
 * 티켓 정보를 나타내는 클래스
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Ticket {
    private Long fee;
    
    /**
     * 생성자로 초기화된 티켓 정보를 반환한다.
     * @param fee 티켓 요금
     */
    public Ticket(Long fee) {
        this.fee = fee;
    }

    /**
     * 티켓 요금을 반환한다.
     * @return 티켓 요금
     */
    public Long getFee() {
        return fee;
    }
}
