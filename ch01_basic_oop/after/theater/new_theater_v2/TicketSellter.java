package ch01_basic_oop.after.theater.new_theater_v2;

import common.Audience;

public class TicketSellter {
    private TicketOffice ticketOffice;
    
    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }

    // public void setTicket(Audience audience) {
    //     if (audience.getBag().hasInvitation()){
    //         Ticket ticket = ticketOffice.getTicket();
    //         audience.getBag().setTicket(ticket);
    //     } else{
    //         Ticket ticket = ticketOffice.getTicket();
    //         audience.getBag().minusAmount(ticket.getFee());
    //         ticketOffice.plusAmount (ticket.getFee());
    //         audience.getBag().setTicket(ticket);
    //     }
    // }
    
    /**
     * 질문: Ticketseller가 Audience에게 setTicket 메시지를 전송하는 이유는 무엇인가?
     * 답변: Audience '가 티켓을 사도록 만드는 것이 목적이다.
     * 수정: 클라이언트가 원하는 것은 buy라는 메시지일 것이다.
     */
    public void sellTo(Audience audience) {
        // ticketOffice.plusAmount(audience.setTicket(ticketOffice.getTicket()));
        ticketOffice.plusAmount(
            audience.buy(ticketOffice.getTicket())
        );
    }
}
