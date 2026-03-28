package ch01_basic_oop.after.theater.new_theater_v2;

import java.util.List;

import common.Money;
import common.Ticket;

public class TicketOffice {
    private List<Ticket> tickets;
    private Money amount;

    public TicketOffice(List<Ticket> tickets, Money amount) {
        this.tickets = tickets;
        this.amount = amount;
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void plusAmount(Long fee) {
        amount.plus(Money.wons(fee));
    }
}
