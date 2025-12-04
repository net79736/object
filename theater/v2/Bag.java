package v2;

/**
 * 관람객이 가지고 올 수 있는 소지품 
 * [초대장, 현금, 티켓]
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Bag {
    // 현금
    private Long amount;
    // 초대장
    private Invitation invitation;
    // 티켓
    private Ticket ticket;

    /**
     * 현금만 가지고 있는 경우
     * 
     * @param amount 현금 
     */
    public Bag(long amount) {
    	this(null, amount);
    }

    /**
     * 초대장과 현금이 있는 경우
     * 
     * @param invitation 초대장
     * @param amount 현금
     */
    public Bag(Invitation invitation, long amount) {
    	this.invitation = invitation;
        this.amount = amount;
    }

    /**
     * 초대장이 있는지 확인
     * 
     * @return boolean
     */
    public boolean hasInvitation() {
    	return invitation != null;
    }

    /**
     * 티켓이 있는지 확인
     * 
     * @return boolean
     */
    public boolean hasTicket() {
    	return ticket != null;
    }

    /**
     * 티켓을 설정
     * 
     * @param ticket 티켓
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * 현금을 차감
     * 
     * @param amount 차감할 현금
     */
    public void minusAmount(Long amount) {
    	this.amount -= amount;
    }
    
    /**
     * 현금을 증가
     * 
     * @param amount 증가할 현금
     */
    public void plusAmount(Long amount) {
    	this.amount += amount;
    }


}
