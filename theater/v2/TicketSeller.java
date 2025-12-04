package v2;

/**
 * 티켓 판매자 정보를 나타내는 클래스
 * 티켓 판매자는 티켓 매표소를 관리한다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class TicketSeller {
    private TicketOffice ticketOffice;

    /**
     * 생성자로 초기화된 티켓 판매자 정보를 반환한다.
     * @param ticketOffice 티켓 매표소
     */
    public TicketSeller(TicketOffice ticketOffice) {
    	this.ticketOffice = ticketOffice;       
    }
    
    /**
     * 티켓 매표소를 반환한다.
     * @return 티켓 매표소
     */
    public TicketOffice getTicketOffice() {
    	return ticketOffice;
    }
    
    /**
     * 관객에게 티켓을 판매한다.
     *  - 객체 내부의 세부적인 사항을 감추는 것을 캡슐화라고 부른다.
     * 
     * @param audience
     */
    public void sellTo(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            // 관객에게 초대장이 있는 경우
            Ticket ticket = ticketOffice.getTicket(); // 티켓을 꺼낸다.
            audience.getBag().setTicket(ticket); // 티켓을 관람객의 가방에 할당
        } else {
            // 관객에게 초대장이 없는 경우
            Ticket ticket = ticketOffice.getTicket(); // 티켓을 꺼낸다.
            audience.getBag().minusAmount(ticket.getFee()); // 요금을 관람객의 가방에서 차감
            ticketOffice.plusAmount(ticket.getFee()); // 요금을 판매소에 추가
            audience.getBag().setTicket(ticket); // 티켓을 관람객의 가방에 할당
        }
    }


}
