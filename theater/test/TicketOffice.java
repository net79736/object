package test;

import java.util.List;

import common.Money;
import common.Ticket;

public class TicketOffice {
    // 티켓
    private List<Ticket> tickets;
    // 영화관 잔액
    private Money amount;

    public TicketOffice(List<Ticket> tickets, Money amount) {
        this.tickets = tickets;
        this.amount = amount;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Money getAmount() {
        return amount;
    }

    public Money plusAmount(Money amount) {
        return this.amount.plus(amount);
    }

    public Ticket issueTicket() {
        if (!tickets.isEmpty()) {
            return tickets.remove(0);
        }
        return null;
    }

}
