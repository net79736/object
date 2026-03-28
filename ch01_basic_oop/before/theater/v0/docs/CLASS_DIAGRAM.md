# 영화 예매 시스템 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 예약 대행 클래스(`ReservationAgency`)를 중심으로 설계되었습니다. 
예약 대행 클래스는 상영 정보, 고객 정보를 받아 할인 조건을 검증하고 최종 예약을 생성합니다.

```mermaid
classDiagram
    class ReservationAgency {
        + reserve(screening: Screening, customer: Customer, audienceCount: int): Reservation
    }

    class Reservation {
        - customer: Customer
        - screening: Screening
        - money: Money
        - audienceCount: int
        + getCustomer(): Customer
        + getScreening(): Screening
        + getMoney(): Money
        + getAudienceCount(): int
        + setCustomer(customer: Customer): void
        + setScreening(screening: Screening): void
        + setMoney(money: Money): void
        + setAudienceCount(audienceCount: int): void
    }

    class Screening {
        - movie: Movie
        - sequence: int
        - whenScreened: LocalDateTime
        + getMovie(): Movie
        + getSequence(): int
        + getWhenScreened(): LocalDateTime
        + setMovie(movie: Movie): void
        + setSequence(sequence: int): void
        + setWhenScreened(whenScreened: LocalDateTime): void
    }

    class Customer {
        - id: Long
        - name: String
        + getId(): Long
        + getName(): String
        + setId(id: Long): void
        + setName(name: String): void
    }

    class Movie {
        - title: String
        - runningTime: Duration
        - fee: Money
        - discountConditions: List~DiscountCondition~
        - movieType: MovieType
        - discountAmount: Money
        - discountPercent: double
        + getMovieType(): MovieType
        + getFee(): Money
        + getDiscountConditions(): List~DiscountCondition~
        + getDiscountAmount(): Money
        + getDiscountPercent(): double
        + setMovieType(movieType: MovieType): void
        + setFee(fee: Money): void
        + setDiscountConditions(discountConditions: List~DiscountCondition~): void
        + setDiscountAmount(discountAmount: Money): void
        + setDiscountPercent(discountPercent: double): void
    }

    class DiscountCondition {
        - type: DiscountConditionType
        - sequence: int
        - dayOfWeek: DayOfWeek
        - startTime: LocalTime
        - endTime: LocalTime
        + getType(): DiscountConditionType
        + getSequence(): int
        + getDayOfWeek(): DayOfWeek
        + getStartTime(): LocalTime
        + getEndTime(): LocalTime
        + setType(type: DiscountConditionType): void
        + setSequence(sequence: int): void
        + setDayOfWeek(dayOfWeek: DayOfWeek): void
        + setStartTime(startTime: LocalTime): void
        + setEndTime(endTime: LocalTime): void
    }

    class MovieType {
        <<enumeration>>
        AMOUNT_DISCOUNT
        PERCENT_DISCOUNT
        NONE_DISCOUNT
    }

    class DiscountConditionType {
        <<enumeration>>
        SEQUENCE
        PERIOD
    }

    class Money {
        - amount: BigDecimal
        + ZERO: Money
        + wons(amount: long): Money
        + wons(amount: double): Money
        + plus(amount: Money): Money
        + minus(amount: Money): Money
        + times(percent: double): Money
        + isLessThan(other: Money): boolean
        + isGreaterThanOrEqual(other: Money): boolean
        + getAmount(): BigDecimal
    }

    ReservationAgency ..> Screening
    ReservationAgency ..> Customer
    ReservationAgency ..> Movie
    ReservationAgency ..> DiscountCondition
    ReservationAgency ..> Reservation
    Reservation "1" -- "1" Customer
    Reservation "1" -- "1" Screening
    Reservation "1" -- "1" Money
    Screening "1" -- "1" Movie
    Movie "1" -- "1..*" DiscountCondition
    Movie "1" -- "1" MovieType
    Movie "1" -- "1" Money
    DiscountCondition "1" -- "1" DiscountConditionType
```

## 예약 도메인

```mermaid
classDiagram
    class ReservationAgency {
        + reserve(screening: Screening, customer: Customer, audienceCount: int): Reservation
    }

    class Reservation {
        - customer: Customer
        - screening: Screening
        - money: Money
        - audienceCount: int
        + getCustomer(): Customer
        + getScreening(): Screening
        + getMoney(): Money
        + getAudienceCount(): int
        + setCustomer(customer: Customer): void
        + setScreening(screening: Screening): void
        + setMoney(money: Money): void
        + setAudienceCount(audienceCount: int): void
    }

    class Customer {
        - id: Long
        - name: String
        + getId(): Long
        + getName(): String
        + setId(id: Long): void
        + setName(name: String): void
    }

    ReservationAgency ..> Reservation
    Reservation "1" -- "1" Customer
    Reservation "1" -- "1" Screening
    Reservation "1" -- "1" Money
```

## 상영 도메인

```mermaid
classDiagram
    class Screening {
        - movie: Movie
        - sequence: int
        - whenScreened: LocalDateTime
        + getMovie(): Movie
        + getSequence(): int
        + getWhenScreened(): LocalDateTime
        + setMovie(movie: Movie): void
        + setSequence(sequence: int): void
        + setWhenScreened(whenScreened: LocalDateTime): void
    }

    class Movie {
        - title: String
        - runningTime: Duration
        - fee: Money
        - discountConditions: List~DiscountCondition~
        - movieType: MovieType
        - discountAmount: Money
        - discountPercent: double
        + getMovieType(): MovieType
        + getFee(): Money
        + getDiscountConditions(): List~DiscountCondition~
        + getDiscountAmount(): Money
        + getDiscountPercent(): double
        + setMovieType(movieType: MovieType): void
        + setFee(fee: Money): void
        + setDiscountConditions(discountConditions: List~DiscountCondition~): void
        + setDiscountAmount(discountAmount: Money): void
        + setDiscountPercent(discountPercent: double): void
    }

    Screening "1" -- "1" Movie
    Movie "1" -- "1..*" DiscountCondition
    Movie "1" -- "1" MovieType
    Movie "1" -- "1" Money
```

## 할인 도메인

```mermaid
classDiagram
    class DiscountCondition {
        - type: DiscountConditionType
        - sequence: int
        - dayOfWeek: DayOfWeek
        - startTime: LocalTime
        - endTime: LocalTime
        + getType(): DiscountConditionType
        + getSequence(): int
        + getDayOfWeek(): DayOfWeek
        + getStartTime(): LocalTime
        + getEndTime(): LocalTime
        + setType(type: DiscountConditionType): void
        + setSequence(sequence: int): void
        + setDayOfWeek(dayOfWeek: DayOfWeek): void
        + setStartTime(startTime: LocalTime): void
        + setEndTime(endTime: LocalTime): void
    }

    class DiscountConditionType {
        <<enumeration>>
        SEQUENCE
        PERIOD
    }

    class MovieType {
        <<enumeration>>
        AMOUNT_DISCOUNT
        PERCENT_DISCOUNT
        NONE_DISCOUNT
    }

    class Movie {
        - movieType: MovieType
        - discountAmount: Money
        - discountPercent: double
        + getMovieType(): MovieType
        + getDiscountAmount(): Money
        + getDiscountPercent(): double
    }

    DiscountCondition "1" -- "1" DiscountConditionType
    Movie "1" -- "1" MovieType
```

## 공통 도메인

```mermaid
classDiagram
    class Money {
        - amount: BigDecimal
        + ZERO: Money
        + wons(amount: long): Money
        + wons(amount: double): Money
        + plus(amount: Money): Money
        + minus(amount: Money): Money
        + times(percent: double): Money
        + isLessThan(other: Money): boolean
        + isGreaterThanOrEqual(other: Money): boolean
        + getAmount(): BigDecimal
    }

    class Reservation {
        - money: Money
        + getMoney(): Money
        + setMoney(money: Money): void
    }

    class Movie {
        - fee: Money
        - discountAmount: Money
        + getFee(): Money
        + getDiscountAmount(): Money
    }

    Reservation "1" -- "1" Money
    Movie "1" -- "1" Money
```

