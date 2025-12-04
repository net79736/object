## 설계 개선하기
코드를 이해하기 어려운 이유는 Theater가 관람객의 가방과 판매원의 매표소에 직접 접근하기 때문
관람객과 판매원이 자신의 일을 스스로 처리해야 한다는 우리의 직관을 벗어난다.

## 해결 방안
Theater가 Audience 와 TicketSeller 에 관해 너무 세세한 부분까지 알지 못하도록 정보를 차단하면 된다.
다시 말해서 `관람객과 판매원을 자율적인 존재로 만들면 되는 것`

`해결 방법은 Audience와 TicketSeller가 직접 Bag과 TicketOffice를 처리하도록 변경하는 것`

Audience는 자신의 가방 안에 초대장이 들어있는지를 스스로 확인
외부(Theater 또는 TicketSeller)에서는 Audience가 Bag의 소유 여부를 관여 X

```java
/**
    * 티켓을 구매한다.
    *  - 초대장을 갖고 있으면 티켓을 받는다.
    * 
    * @param ticket 티켓
    * @return 티켓 구매 비용
    */
public Long buy(Ticket ticket) {
    if (bag.hasInvitation()) { // 초대장을 갖고 있니?
        bag.setTicket(ticket); // 티켓을 받는다.
        return 0L; // 티켓 구매 비용 없음
    } else {
        bag.minusAmount(ticket.getFee()); // 티켓 구매 비용 차감
        bag.setTicket(ticket); // 티켓을 받는다.
        return ticket.getFee(); // 티켓 구매 비용 반환
    }
}
```



