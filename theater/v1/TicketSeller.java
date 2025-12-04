package v1;

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
    
}
