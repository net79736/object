package test;

import common.Bag;
import common.Ticket;

public class Audience {

    private Long id;
    private String name;
    private Bag bag;

    public Audience(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bag getBag() {
        return bag;
    }

    public void buy(Ticket ticket) {
        bag.hold(ticket);
    }
}
