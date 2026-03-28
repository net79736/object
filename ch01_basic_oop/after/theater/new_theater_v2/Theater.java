package ch01_basic_oop.after.theater.new_theater_v2;

import common.Audience;

public class Theater {
    private TicketSellter ticketSellter;

    public Theater(TicketSellter ticketSellter) {
        this.ticketSellter = ticketSellter;
    }

    /**
     // setTicket보다 sellTo가 Audience에 티켓을 판매한다는 의도가 더 명확함
     * @param audience
     */
    public void enter(Audience audience) {
        // ticketSellter.setTicket(audience);
        ticketSellter.sellTo(audience);
    }
}
