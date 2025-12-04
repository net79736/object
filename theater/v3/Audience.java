package v3;

/**
 * 관람객 정보를 나타내는 클래스
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Audience {
    // 가방
    private Bag bag;

    /**
     * 생성자로 초기화된 관람객 정보를 반환한다.
     * @param bag 가방
     */
    public Audience(Bag bag) {
        this.bag = bag;
    }

    /**
     * 가방을 반환한다.
     * @return 가방
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * 티켓을 구매한다.
     *  - 초대장을 갖고 있으면 티켓을 받는다.
     * 
     * @param ticket 티켓
     * @return 티켓 구매 비용
     */
    public Long buy(Ticket ticket) {
        if (bag.hasInvitation()) { // 초대장을 갖고 있니?
            bag.setTicket(ticket); // 티켓을 받는다.
            return 0L; // 티켓 구매 비용 없음
        } else {
            bag.minusAmount(ticket.getFee()); // 티켓 구매 비용 차감
            bag.setTicket(ticket); // 티켓을 받는다.
            return ticket.getFee(); // 티켓 구매 비용 반환
        }
    }
}
