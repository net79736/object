# 목표
이번 장의 목표는 훌륭한 인터페이스를 얻기 위한 방법

# 두 객체 사이의 협력 관계
협력은 어떤 객체가 다른 객체에게 무언가를 요청할 때 시작
클라이언트-서버 모델이다(단방향 상호작용)

```java
Screening 
→ 클라이언트 (예매해라)

Movie
→ 서버 (티켓 가격 계산)
→ 클라이언트 (할인 가격 요청)

DiscountPolicy
→ 서버 (할인 가격 계산)
```

# 퍼블릭 인터페이스
 - 객체가 의사소통을 위해 외부에 공개하는 메시지의 집합 (1)
 - SequenceCondition, Periodcondition에 정의된 isSatisfiedBy(2)


# 퍼블릭 인터페이스의 품질에 영향을 미치는 것
1. 디미터 법칙
2. 묻지 말고 시켜라
3. 의도를 드러내는 인터페이스
4. 명령-쿼리 분리

## 문제점
Screening 변경 시 -> ReservationAgency 함께 변경 필요
```java
public class ReservationAgency {
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
	Movie movie = screening.getMovie(); // 요기요기
	
	for (DiscountCondition condition : movie.getDiscountConditions()) { // 요기요기
	  ...
	}
  }
}
```

## 디미터 법칙
객체들의 협력 경로를 제한하여 결합도를 낮추는 것
아래 조건을 만족하는 인스턴스에만 메시지를 전송하도록 코딩
■ this 객체
■ 메서드의 매개변수
■ this의 속성
■ this의 속성인 컬렉션의 요소
■ 메서드 내에서 생성된 지역 객체
```java
/**
 * ReservationAgency가 Screening의 내부 구조에 결합돼 있지 않기 때문에
 * Screening의 내부 구현을 변경할 때 ReservationAgency를 함께 변경할 필요가 없다.
 */
public class ReservationAgency {
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    Money fee = screening.calculateFee(audienceCount); // screening 인스턴스에게만 메시지를 전송
	return new Reservation(customer, screening, fee, audienceCount);
  }
}
```

## 어떻게 고쳐야 맞는가?
```java
// 문제점 있는 코드
screening.getMovie().getDiscountConditions();
// screening: 전송자
screening.getMovie(); : 수신자
// 아래와 같이 코드 수정
screening.calculateFee(audienceCount);
```

## 디미터 법칙이란?
### 1. 묻지말고 시켜라
ReservationAgency는 Screening에게 요금을 계산하도록 요청
 → 요금을 계산하는데 필요한 정보를 잘 아니깐

객체의 외부에서 해당 객체의 상태를 기반으로 무언가 결정을 내리는 것은 캡슐화 위반
 → getXXX() 코드 자체 쓰는 것을 캡슐화 위반이라고 보는것 같음

## 2. 클라이언트의 의도를 드러내야 한다.
```java
public class PeriodCondition {
    public boolean isSatisfiedByPeriod (Screening screening) { ... }
}

public class SequenceCondition {
    public boolean isSatisfiedBySequence(Screening screening) { ... }
}
```
위의 코드가 좋지 못한 이유
1. 클라이언트의 관점에서 두 함수 모두 할인 조건을 판단하는 동일한 작업을 수행한다
2. 따라서 클라이언트로 하여금 협력하는 객체의 종류를 알도록 강요

## 하나의 구현을 가진 메시지의 이름을 일반화하도록 도와주는 간단한 훈련 방법을 소개
매우 다른 두번째 구현을 상상하라. 그러고는 해당 메서드에 동일한 이름을 붙인다고 상상하자
그 순간 가장 추상적인 이름을 메서드에 붙일 것이다.


## 영화관 코드
이 코드에서 Theater 는 Audience뿐만 아니라 Audience 내부에 포함된
Bag에게도 메시지를 전송한다.
결과적으로 Theater는 Audience의 퍼블릭 인터페이스뿐만 아니라
내부 구조에 대해서도 결합된다.

```java
public class Theater {
    private TicketSellter ticketSellter;

    public Theater(TicketSellter ticketSellter) {
        this.ticketSellter = ticketSellter;
    }

    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSellter.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSellter.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSellter.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```

Theater는 TicketSeller와 Audience에게 원하는 작업을 시켜야 한다.
변경된 코드

```java
public class Theater {
  public void enter(Audience audience) {
    ticketSeller.setTicket(audience);
  } 
}

public class TicketSeller {
    public void setTicket(Audience audience) {
        if (audience.getBag().hasInvitation()){
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().setTicket(ticket);
        } else{
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().minus Amount(ticket.getFee());
            ticketOffice.plusAmount (ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```

이제 TicketSeller에 초점을 맞춰보자.
TicketSeller가 원하는 것은 Audience가 Ticket을 보유하도록 만드는 것이다.
따라서 Audience에게 setTicket 메서드를 추가하고 스스로 티켓을 가지도록 만들자.

```java
public class Audience {
    public Long setTicket(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}

public class TicketSeller {
  public void setTicket(Audience audience) {
    ticketOffice.plusAmount(audience.setTicket(ticketOffice.getTicket()));
  }
}
```

문제는 Audience다. Audience가 hasInvitation 메서드를 이용해
Bag 클래스의 내부 구조에 직접 접근을 하기 때문에 디미터 법칙을 위반한다.

Audience의 setTicket 메서드 구현 ▶️(이동) Bag의 setTicket 메서드

```java
public class Audience {
  public Long setTicket(Ticket ticket) {
    return bag.setTicket(ticket);
  }
}

public class Bag {
    boolean invitation;
    private Long amount;

    public void setTicket(Ticket ticket) {
        if (hasInvitation()) {
            this.ticket = ticket;
            return 0L;
        } else {
            this.ticket = ticket;
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    private boolean hasInvitation() {
        return invitation != null;
    }
    
    private void minusAmount(Long amount) {
        this.amount -= amount;
    }
}
```

질문: Theater가 TicketSeller에게 setTicket 메시지를 전송해서 
얻고 싶었던 결과는 무엇일까?
답변: 바로 Audience 에게 티켓을 판매하는 것이다.
수정: setTicket보다 sellTo가 의도를 더 명확하게 표현하는 메시지일 것이다.

질문: Ticketseller가 Audience에게 
setTicket 메시지를 전송하는 이유는 무엇인가?
답변: Audience가 티켓을 사도록 만드는 것이 목적이다.
수정: 클라이언트가 원하는 것은 buy라는 메시지일 것이다.

질문: Audience가 Bag에게 setTicket 메시지를 전송하면서 의도한 것은 무엇일까?
답변: 티켓을 보관하도록 만드는 것이 목적이다.
수정: Bag이 hold 메시지를 수신한다면 클라이언트의 의도를 좀 더 분명하게 표현할 수 있을 것이다.



# 6.5.7 명령-쿼리 분리 원칙





