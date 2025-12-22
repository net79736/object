```java
public class ServiceLocator {
    private static ServiceLocator instance = new ServiceLocator();
    private Map<Class<?>, Object> services = new HashMap<>();
    
    public static void register(Class<?> type, Object service) {
        instance.services.put(type, service);
    }
    
    public static <T> T get(Class<T> type) {
        return (T) instance.services.get(type);
    }
}

public class Order {
    private List<OrderItem> items;
    private Customer customer;
    
    public Money calculateTotalPrice() {
        Money subtotal = calculateSubtotal();
        
        // DiscountPolicy를 SERVICE LOCATOR에서 가져옴
        DiscountPolicy discountPolicy = 
            ServiceLocator.get(DiscountPolicy.class);
        
        return discountPolicy.apply(customer, subtotal);
    }
    
    private Money calculateSubtotal() {
        return items.stream()
            .map(OrderItem::getPrice)
            .reduce(Money.ZERO, Money::add);
    }
}

public class Application {
    public static void main(String[] args) {
        // 애플리케이션 초기화
        ServiceLocator.register(DiscountPolicy.class, 
                               new VipDiscountPolicy());
        
        // 주문 처리
        Order order = createOrder();
        Money price = order.calculateTotalPrice();
    }
}
```
질문:

이 코드가 "숨겨진 의존성"을 가지는 이유를 설명하시오
이 코드를 테스트할 때 어떤 문제가 발생하는가?
의존성 주입으로 개선하고, 두 방식을 비교하시오

문제점:
1. 캡슐화 위반
  * Order가 DiscountPolicy에 의존한다는 사실이 숨겨져 있음. 
  * 코드만 봐서는 알 수 없음

2. 의존성 설정과 사용의 시간적 분리
```java
ServiceLocator.register(...);  // 여기서 설정
// ... 100줄의 코드 ...
order.calculateTotalPrice();   // 여기서 사용
// ⬆️ 중간에 누군가 register를 빼먹으면?
```

3. 디버깅 어려움
```java
// 테스트 실패 시:
// "Order는 왜 DiscountPolicy가 null이지?"
// ⇒ ServiceLocator.register() 호출 찾아 헤매야 함
```

의존성 주입으로 해결해야 함. 이 패턴 쓰지말라고 권장하고 있음