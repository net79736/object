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
