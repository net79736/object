package v3;

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
        Long amount = audience.buy(ticketOffice.getTicket()); // 티켓을 구매한다.
        ticketOffice.plusAmount(amount); // 티켓 구매 비용을 판매소에 추가
    }


}
