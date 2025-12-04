package v2;

/**
 * 극장 정보를 나타내는 클래스
 * 극장은 티켓 판매자를 관리한다.
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    /**
     * 관람객이 극장에 입장한다.
     * @param audience 관람객
     */
    public void enter(Audience audience) {
        // Theater는 오직 TicketSeller의 인터페이스(interface)에만 의존
        // 객체를 인터페이스와 구현으로 나누고 인터페이스만을 공개하는 것은 가장 기본적인 설계 원칙
        ticketSeller.sellTo(audience);
    }
}
