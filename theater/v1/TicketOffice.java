package v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 매표소 정보를 나타내는 클래스
 * 매표소는 티켓을 판매하고 판매 금액을 관리한다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class TicketOffice {
    private Long amount; // 매표소 잔액
    private List<Ticket> tickets = new ArrayList(); // 매표소가 보유한 티켓 목록

    /**
     * 생성자로 초기화된 매표소 정보를 반환한다.
     * 
     * @param amount 매표소 잔액
     * @param tickets 티켓 목록
     */
    public TicketOffice(Long amount, Ticket ... tickets) {
    	this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }
    
    /*
     * 매표소가 보유한 티켓 중 하나를 반환한다.
     * @return 티켓
     */
    public Ticket getTicket() {
    	return tickets.remove(0);
    }

    /**
     * 매표소 잔액을 차감한다.
     * @param amount 차감할 금액
     */
    public void minusAmount(Long amount) {
    	this.amount -= amount;
    }
    
    /**
     * 매표소 잔액을 증가시킨다.
     * @param amount 증가할 금액
     */
    public void plusAmount(Long amount) {
    	this.amount += amount;
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 30;

        List<Integer> list = new ArrayList<>();
        list.add(100000);

        list.addAll(Arrays.asList(a, b, c));
        System.out.println(list);

        list.remove(0);
        System.out.println(list);
    }


}
