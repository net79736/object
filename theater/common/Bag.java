package common;

public class Bag {
    private Invitation invitation;
    private Money amount;
    private Ticket ticket;

    public Bag(Invitation invitation, Money amount, Ticket ticket) {
        this.invitation = invitation;
        this.amount = amount;
        this.ticket = ticket;
    }

    public Money getAmount() {
        return amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public void minusAmount(Long fee) {
        amount.minus(Money.wons(fee));
    }
}
