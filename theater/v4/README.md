[도메인 클래스 다이어그램](./domain-class-diagram.png)

## 자율적인 객체
 - 자율적인 객체가 되기 위해서는 외부의 간섭을 최소화해야 한다. 객체에게 원하는 것을 요청 후 믿고 기다려야 한다.

---

## API 문서

### 1. Money

금액을 나타내는 값 객체(Value Object)입니다.

#### 1.1. 정적 팩토리 메서드

##### `wons(long amount)`

- **Method**: `Money wons(long amount)`
- **Description**: long 타입 금액으로 Money 객체를 생성합니다.
- **Parameters**:
  - `amount`: 금액 (long)
- **Returns**: `Money` - 생성된 Money 객체

##### `wons(double amount)`

- **Method**: `Money wons(double amount)`
- **Description**: double 타입 금액으로 Money 객체를 생성합니다.
- **Parameters**:
  - `amount`: 금액 (double)
- **Returns**: `Money` - 생성된 Money 객체

#### 1.2. 인스턴스 메서드

##### `plus(Money amount)`

- **Method**: `Money plus(Money amount)`
- **Description**: 현재 요금에 추가 요금을 더한 새로운 Money 객체를 반환합니다.
- **Parameters**:
  - `amount`: 추가할 금액 (Money)
- **Returns**: `Money` - 계산된 새로운 Money 객체

##### `minus(Money amount)`

- **Method**: `Money minus(Money amount)`
- **Description**: 현재 요금에서 차감 요금을 뺀 새로운 Money 객체를 반환합니다.
- **Parameters**:
  - `amount`: 차감할 금액 (Money)
- **Returns**: `Money` - 계산된 새로운 Money 객체

##### `times(double percent)`

- **Method**: `Money times(double percent)`
- **Description**: 현재 요금에 특정 비율을 곱한 새로운 Money 객체를 반환합니다.
- **Parameters**:
  - `percent`: 곱할 비율 (double)
- **Returns**: `Money` - 계산된 새로운 Money 객체

##### `isLessThan(Money other)`

- **Method**: `boolean isLessThan(Money other)`
- **Description**: 현재 금액이 인자로 들어온 금액보다 작은지 확인합니다.
- **Parameters**:
  - `other`: 비교할 금액 (Money)
- **Returns**: `boolean` - 현재 금액이 더 작으면 true, 그렇지 않으면 false

##### `isGreaterThanOrEqual(Money other)`

- **Method**: `boolean isGreaterThanOrEqual(Money other)`
- **Description**: 현재 금액이 인자로 들어온 금액보다 같거나 큰지 확인합니다.
- **Parameters**:
  - `other`: 비교할 금액 (Money)
- **Returns**: `boolean` - 현재 금액이 같거나 크면 true, 그렇지 않으면 false

##### `getAmount()`

- **Method**: `BigDecimal getAmount()`
- **Description**: 현재 금액을 BigDecimal 타입으로 반환합니다.
- **Returns**: `BigDecimal` - 현재 금액

---

### 2. Movie

영화 정보를 나타내는 클래스입니다.

#### 2.1. 생성자

##### `Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy)`

- **Method**: `public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy)`
- **Description**: 영화 객체를 생성합니다.
- **Parameters**:
  - `title`: 영화 제목 (String)
  - `runningTime`: 상영 시간 (Duration)
  - `fee`: 영화 요금 (Money)
  - `discountPolicy`: 할인 정책 (DiscountPolicy)

#### 2.2. 인스턴스 메서드

##### `getFee()`

- **Method**: `Money getFee()`
- **Description**: 영화의 기본 요금을 반환합니다.
- **Returns**: `Money` - 영화의 기본 요금

##### `calculateMovieFee(Screening screening)`

- **Method**: `Money calculateMovieFee(Screening screening)`
- **Description**: 상영 정보를 기반으로 할인을 적용한 영화 요금을 계산합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `Money` - 할인을 적용한 영화 요금

---

### 3. Screening

영화 상영 정보를 나타내는 클래스입니다.

#### 3.1. 생성자

##### `Screening(Movie movie, int sequence, LocalDateTime whenScreened)`

- **Method**: `public Screening(Movie movie, int sequence, LocalDateTime whenScreened)`
- **Description**: 상영 객체를 생성합니다.
- **Parameters**:
  - `movie`: 영화 객체 (Movie)
  - `sequence`: 상영 순번 (int)
  - `whenScreened`: 상영 시작 시간 (LocalDateTime)

#### 3.2. 인스턴스 메서드

##### `getStartTime()`

- **Method**: `LocalDateTime getStartTime()`
- **Description**: 상영 시작 시간을 반환합니다.
- **Returns**: `LocalDateTime` - 상영 시작 시간

##### `isSequence(int sequence)`

- **Method**: `boolean isSequence(int sequence)`
- **Description**: 현재 상영의 순번이 인자로 들어온 순번과 일치하는지 확인합니다.
- **Parameters**:
  - `sequence`: 확인할 순번 (int)
- **Returns**: `boolean` - 순번이 일치하면 true, 그렇지 않으면 false

##### `getMovieFee()`

- **Method**: `Money getMovieFee()`
- **Description**: 영화의 기본 요금을 반환합니다.
- **Returns**: `Money` - 영화의 기본 요금

##### `reserve(Customer customer, int audienceCount)`

- **Method**: `Reservation reserve(Customer customer, int audienceCount)`
- **Description**: 고객과 인원수를 기반으로 영화 예매를 처리하고 예매 정보를 반환합니다.
- **Parameters**:
  - `customer`: 고객 객체 (Customer)
  - `audienceCount`: 인원수 (int)
- **Returns**: `Reservation` - 생성된 예매 정보

---

### 4. Reservation

영화 예매 정보를 나타내는 클래스입니다.

#### 4.1. 생성자

##### `Reservation(Customer customer, Screening screening, Money fee, int audienceCount)`

- **Method**: `public Reservation(Customer customer, Screening screening, Money fee, int audienceCount)`
- **Description**: 예매 객체를 생성합니다.
- **Parameters**:
  - `customer`: 고객 객체 (Customer)
  - `screening`: 상영 정보 (Screening)
  - `fee`: 요금 (Money)
  - `audienceCount`: 인원 수 (int)

---

### 5. Customer

고객을 나타내는 클래스입니다.

- 현재 비어있는 클래스입니다.

---

### 6. DiscountCondition (Interface)

할인 조건을 나타내는 인터페이스입니다.

#### 6.1. 메서드

##### `isSatisfiedBy(Screening screening)`

- **Method**: `boolean isSatisfiedBy(Screening screening)`
- **Description**: 상영 정보가 할인 조건을 만족하는지 확인합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `boolean` - 할인 조건을 만족하면 true, 그렇지 않으면 false

---

### 7. PeriodCondition

상영 요일과 시간에 따른 할인 조건을 나타내는 클래스입니다.

#### 7.1. 생성자

##### `PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime)`

- **Method**: `public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime)`
- **Description**: 기간 조건 객체를 생성합니다.
- **Parameters**:
  - `dayOfWeek`: 상영 요일 (DayOfWeek)
  - `startTime`: 상영 시작 시간 (LocalTime)
  - `endTime`: 상영 종료 시간 (LocalTime)

#### 7.2. 인스턴스 메서드

##### `isSatisfiedBy(Screening screening)`

- **Method**: `boolean isSatisfiedBy(Screening screening)`
- **Description**: 상영 시간이 지정된 요일과 시간 범위 내에 있는지 확인합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `boolean` - 조건을 만족하면 true, 그렇지 않으면 false

---

### 8. SequenceCondition

상영 순번에 따른 할인 조건을 나타내는 클래스입니다.

#### 8.1. 생성자

##### `SequenceCondition(int sequence)`

- **Method**: `public SequenceCondition(int sequence)`
- **Description**: 순번 조건 객체를 생성합니다.
- **Parameters**:
  - `sequence`: 상영 순번 (int)

#### 8.2. 인스턴스 메서드

##### `isSatisfiedBy(Screening screening)`

- **Method**: `boolean isSatisfiedBy(Screening screening)`
- **Description**: 상영 순번이 지정된 순번과 일치하는지 확인합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `boolean` - 순번이 일치하면 true, 그렇지 않으면 false

---

### 9. DiscountPolicy (Abstract Class)

할인 정책을 나타내는 추상 클래스입니다.

#### 9.1. 생성자

##### `DiscountPolicy(DiscountCondition... conditions)`

- **Method**: `public DiscountPolicy(DiscountCondition... conditions)`
- **Description**: 할인 정책 객체를 생성합니다.
- **Parameters**:
  - `conditions`: 할인 조건들 (DiscountCondition...)

#### 9.2. 인스턴스 메서드

##### `calculateDiscountAmount(Screening screening)`

- **Method**: `Money calculateDiscountAmount(Screening screening)`
- **Description**: 상영 정보를 기반으로 할인 금액을 계산합니다. 할인 조건을 만족하는 경우 `getDiscountAmount`를 호출하여 할인 금액을 반환하고, 만족하지 않는 경우 0원을 반환합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `Money` - 할인 금액 (조건을 만족하지 않으면 Money.ZERO)

##### `getDiscountAmount(Screening screening)` (Abstract)

- **Method**: `protected abstract Money getDiscountAmount(Screening screening)`
- **Description**: 실제 할인 금액을 계산하는 추상 메서드입니다. 하위 클래스에서 구현해야 합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `Money` - 할인 금액

---

### 10. AmountDiscountPolicy

고정 금액 할인 정책을 나타내는 클래스입니다.

#### 10.1. 생성자

##### `AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions)`

- **Method**: `public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions)`
- **Description**: 고정 금액 할인 정책 객체를 생성합니다.
- **Parameters**:
  - `discountAmount`: 할인 금액 (Money)
  - `conditions`: 할인 조건들 (DiscountCondition...)

#### 10.2. 인스턴스 메서드

##### `getDiscountAmount(Screening screening)`

- **Method**: `protected Money getDiscountAmount(Screening screening)`
- **Description**: 고정 금액 할인 정책을 적용하여 할인 금액을 반환합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `Money` - 고정 할인 금액

---

### 11. PercentDiscountPolicy

비율 할인 정책을 나타내는 클래스입니다.

#### 11.1. 생성자

##### `PercentDiscountPolicy(double percent, DiscountCondition... conditions)`

- **Method**: `public PercentDiscountPolicy(double percent, DiscountCondition... conditions)`
- **Description**: 비율 할인 정책 객체를 생성합니다.
- **Parameters**:
  - `percent`: 할인 비율 (double, 예: 0.1 = 10%)
  - `conditions`: 할인 조건들 (DiscountCondition...)

#### 11.2. 인스턴스 메서드

##### `getDiscountAmount(Screening screening)`

- **Method**: `protected Money getDiscountAmount(Screening screening)`
- **Description**: 비율 할인 정책을 적용하여 할인 금액을 계산합니다. 영화 요금에 할인 비율을 곱한 값을 반환합니다.
- **Parameters**:
  - `screening`: 상영 정보 (Screening)
- **Returns**: `Money` - 계산된 할인 금액

