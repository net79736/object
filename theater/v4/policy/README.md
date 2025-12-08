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
