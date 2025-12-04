package v1;

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
        if (audience.getBag().hasInvitation()) {
            // 초대장이 있는 경우
        	Ticket ticket =  ticketSeller.getTicketOffice().getTicket(); // 티켓을 판매소에서 가져옴
            audience.getBag().setTicket(ticket); // 티켓을 관람객의 가방에 할당
        } else {
            // 초대장이 없는 경우
        	Ticket ticket =  ticketSeller.getTicketOffice().getTicket(); // 티켓 가져옴
            audience.getBag().minusAmount(ticket.getFee()); // 요금을 관람객의 가방에서 차감
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee()); // 요금을 판매소에 추가
            audience.getBag().setTicket(ticket); // 티켓을 관람객의 가방에 할당
        }
    }
}
