중요한 것은 객체들이 주고 받는 메시지다.
협력에 적합한 객체를 설계하기 위해서는 아래 2가지를 함께 고려해야 한다.
1. 외부에 전송하는 메시지
2. 객체가 수신하는 메시지

객체가 의사소통을 위해 외부에 공개하는 메시지의 집합을 퍼블릭 인터페이스라고 부른다.

퍼블릭 인터페이스의 품질에 영향을 미치는 원칙과 기법
* 디미터 법칙
* 묻지 말고 시켜라
* 의도를 드러내는 인터페이스
* 명령-쿼리 분리

클래스 내부의 메서드가 아래 조건을 만족하는 인스턴스에만 메시지를 전송하도록 프로그래밍해야 한다라고 이해하자.

■ this 객체
■ 메서드의 매개변수
■ this의 속성
■ this의 속성인 컬렉션의 요소
■ 메서드 내에서 생성된 지역 객체

6.2.1.2 디미터 법칙이란?
객체의 내부 구조를 묻는 메시지가 아니라 수신자에게 무언가를 시키는 메시지를 만들도록 프로그래밍 해라.
(이 맥락에서 "객체 === 수신" 인 것으로 보임)

6.2.2 묻지말고 시켜라
앞에서 ReservationAgency는 Screening 내부의 Movie에 접근하는 대신 Screening에게 직접 요금을 계산하도록 요청했다. 요금을 계산하는데 필요한 정보를 잘 알고 있는 Screening에게 요금을 계산할 책임을 할당한 것이다.
메시지 전송자는 메시지 수신자의 상태를 바꿔서는 안된다. 객체의 외부에서 해당 객체의 상태를 기반으로 무언가 결정을 내리는 것은 객체의 캡슐화를 위반한다.
내부의 상태를 이용해 어떤 결정을 내리는 로직이 객체 외부에 존재하 는가? 그렇다면 해당 객체가 책임져야 하는 어떤 행동이 객체 외부로 누수된 것이다.

6.2.3 의도를 드러내는 인터페이스 (매우 중요)
6.2.3.1 메서드가 작업을 어떻게 수행하는지를 나타내도록 이름 짓는 것
```java
public class Period Condition {
  public boolean isSatisfiedByPeriod (Screening screening) { ... }
}

public class Sequence Condition {
  public boolean isSatisfiedBySequence(Screening screening) { ... }
}
```

* 클라이언트의 관점에서 isSatisfiedByPeriod 와 isSatisfiedBySequence 모두 할인 조건을 판단하는 동일한 작업을 수행한다. 하지만 메서드의 이름이 다르기 때문에 두 메서드의 내부 구현을 정확하게 이해하지 못한다면 두 메서드가 동일한 작업을 수행한다는 사실을 알아채기 어렵다.
* 클라이언트로 하여금 협력하는 객체의 종류를 알도록 강요한다. PeriodCondition을 사용하는 코드를 SequenceCondition을 사용하도록 변경하려면 단순히 참조하는 객체를 변경하는 것뿐만 아니라 호출하는 메서드를 변경해야 한다.

6.2.3.2 "어떻게"가 아니라 "무엇"을 하는지를 드러내는 것
무엇을 하는지를 드러내도록 메서드의 이름을 짓기 위해서는 객체가 협력 안에서 수행해야 하는 책임에 관해 고민해야 한다.
클라이언트의 관점에서 두 메서드는 할인 여부를 판단하기 위한 작업을 수행한다.

```java
public class PeriodCondition {
  public boolean isSatisfiedBy(Screening screening) { ... }
}

public class SequenceCondition {
  public boolean isSatisfiedBy (Screening screening) { ... }
}
```

(매우 중요)
하나의 구현을 가진 메시지의 이름을 일반화하도록 도와주는 간단한 훈련 방법을 소개하겠다. 
매우 다른 두번째 구현을 상상하라. 그러고는 해당 메서드에 동일한 이름을 붙인다고 상상해보라. 
그렇게 하면 아마도 그 순간에 여러분이 할 수 있는 한 가장 추상적인 이름을 메서드에 붙일 것이다.
해당 메서드가 클라이언트의 의도를 드러내야 한다.

6.2.4 함께 모으기
```java
audience.getBag().minusAmount(ticket.getFee());
```

이 코드에서 Theater 는 Audience뿐만 아니라 Audience 내부에 포함된 Bag에게도 메시지를 전송한다. 
결과적으로 Theater는 Audience의 퍼블릭 인터페이스뿐만 아니라 내부 구조에 대해서도 결합된다.


