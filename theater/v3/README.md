## 또 다른 문제
Bag 인스턴스에 접근하는 객체가 Theater에서 TicketSeller로 바뀌었을 뿐 `Audience는 여전히 자율적인 존재가 아닌 것`이다.

## 이 또한 캡슐화
* Bag에 접근하는 모든 로직을 Audience 내부로 감추기 위해 `Audience에 buy 메서드를 추가`
* TicketSeller의 sellTo 메서드에서 getBag 메서드에 접근하는 부분을 buy 메서드로 옮기자

## 책임의 이동
1. Ticketseller의 책임은 무엇인가?
 - 티켓을 판매 하는 것이다.

2. Audience의 책임은 무엇인가?
 - 티켓을 사는 것이다.

3. Theater의 책임은 무엇인가?
 - 관람객을 입장시키는 것이다.