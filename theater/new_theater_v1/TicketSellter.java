package new_theater_v1;

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
    
    public void setTicket(Audience audience) {
        ticketOffice.plusAmount(audience.setTicket(ticketOffice.getTicket()));
    }
}
