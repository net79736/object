package common;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public boolean hasInvitation() {
        return bag.getInvitation() != null;
    }

    // public Long setTicket(Ticket ticket) {
    //     if (bag.hasInvitation()) {
    //         bag.setTicket(ticket);
    //         return 0L;
    //     } else {
    //         bag.setTicket(ticket);
    //         bag.minusAmount(ticket.getFee());
    //         return ticket.getFee();
    //     }
    // }

    public Long setTicket(Ticket ticket) {
        return bag.setTicket(ticket);
    }
}
