package test;

import common.Money;
import common.Ticket;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }

    public void sellTo(Audience audience) {
        Ticket ticket = ticketOffice.issueTicket(); // 티켓 발권
        audience.buy(ticket); // 티켓을 구매

        Long paidAmount = ticket.getFee(); // 티켓 요금
        // 매표소에 돈을 입금한다 (묻지 말고 시키기!)
        ticketOffice.plusAmount(Money.wons(paidAmount));
    }

}
