package new_theater_v1;

import common.Audience;

public class Theater {
    private TicketSellter ticketSellter;

    public Theater(TicketSellter ticketSellter) {
        this.ticketSellter = ticketSellter;
    }

    public void enter(Audience audience) {
        ticketSellter.setTicket(audience);
        // if (audience.getBag().hasInvitation()) {
        //     // 초대장이 있는 경우
        //     Ticket ticket = ticketSellter.getTicketOffice().getTicket(); // 티켓 판매소에서 티켓을 받음
        //     audience.getBag().setTicket(ticket); // 관객의 가방에 티켓을 설정
        // } else {
        //     // 초대장이 없는 경우
        //     Ticket ticket = ticketSellter.getTicketOffice().getTicket(); // 티켓 판매소에서 티켓을 받음
        //     audience.getBag().minusAmount(ticket.getFee()); // 관객의 가방에서 티켓 요금을 차감
        //     ticketSellter.getTicketOffice().plusAmount(ticket.getFee()); // 티켓 판매소에서 티켓 요금을 추가
        //     audience.getBag().setTicket(ticket); // 관객의 가방에 티켓을 설정
        // }
    }
}
