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

    /**
     * 질문: Audience가 Bag에게 setTicket 메시지를 전송하면서 의도한 것은 무엇일까?
     * 답변: 티켓을 보관하도록 만드는 것이 목적이다.
     * 수정: Bag이 hold 메시지를 수신한다면 클라이언트의 의도를 좀 더 분명하게 표현할 수 있을 것이다.
     */
    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
