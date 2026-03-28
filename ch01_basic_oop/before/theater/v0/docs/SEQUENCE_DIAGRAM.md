# SEQUENCE DIAGRAM

## 영화 예매 시퀀스 다이어그램

영화 예매 전체 흐름에서 성공 케이스 중심으로 시퀀스 다이어그램을 작성했습니다. 
이 시퀀스 다이어그램은 영화 예매 시스템의 전체적인 흐름을 직관적으로 보여주며, 
할인 조건 검증 및 요금 계산 단계를 포함하고 있습니다.

### 영화 예매 (할인 조건 검증 및 요금 계산)

```mermaid
sequenceDiagram
    actor Client
    participant ReservationAgency
    participant Screening
    participant Movie
    participant DiscountCondition
    participant Reservation
    title 영화 예매 시퀀스 다이어그램
    
    Client ->> ReservationAgency: reserve(screening, customer, audienceCount)
    
    ReservationAgency ->> Screening: getMovie()
    Screening -->> ReservationAgency: movie 반환
    
    ReservationAgency ->> Movie: getDiscountConditions()
    Movie -->> ReservationAgency: discountConditions 반환
    
    loop 각 할인 조건에 대해
        ReservationAgency ->> DiscountCondition: getType()
        DiscountCondition -->> ReservationAgency: type 반환 (PERIOD or SEQUENCE)
        
        alt PERIOD 타입인 경우
            ReservationAgency ->> Screening: getWhenScreened()
            Screening -->> ReservationAgency: whenScreened 반환
            ReservationAgency ->> DiscountCondition: getDayOfWeek()
            DiscountCondition -->> ReservationAgency: dayOfWeek 반환
            ReservationAgency ->> DiscountCondition: getStartTime()
            DiscountCondition -->> ReservationAgency: startTime 반환
            ReservationAgency ->> DiscountCondition: getEndTime()
            DiscountCondition -->> ReservationAgency: endTime 반환
            ReservationAgency ->> ReservationAgency: 기간 할인 조건 검증
        else SEQUENCE 타입인 경우
            ReservationAgency ->> Screening: getSequence()
            Screening -->> ReservationAgency: sequence 반환
            ReservationAgency ->> DiscountCondition: getSequence()
            DiscountCondition -->> ReservationAgency: sequence 반환
            ReservationAgency ->> ReservationAgency: 순서 할인 조건 검증
        end
        
        alt 할인 조건 만족 시
            ReservationAgency ->> ReservationAgency: discountable = true, 루프 종료
        end
    end
    
    alt 할인 가능한 경우
        ReservationAgency ->> Movie: getMovieType()
        Movie -->> ReservationAgency: movieType 반환
        
        alt AMOUNT_DISCOUNT 타입
            ReservationAgency ->> Movie: getDiscountAmount()
            Movie -->> ReservationAgency: discountAmount 반환
            ReservationAgency ->> ReservationAgency: discountAmount 설정
        else PERCENT_DISCOUNT 타입
            ReservationAgency ->> Movie: getFee()
            Movie -->> ReservationAgency: fee 반환
            ReservationAgency ->> Movie: getDiscountPercent()
            Movie -->> ReservationAgency: discountPercent 반환
            ReservationAgency ->> ReservationAgency: fee * discountPercent 계산
        else NONE_DISCOUNT 타입
            ReservationAgency ->> ReservationAgency: discountAmount = ZERO
        end
        
        ReservationAgency ->> Movie: getFee()
        Movie -->> ReservationAgency: fee 반환
        ReservationAgency ->> ReservationAgency: fee - discountAmount 계산
    else 할인 불가능한 경우
        ReservationAgency ->> Movie: getFee()
        Movie -->> ReservationAgency: fee 반환
        ReservationAgency ->> ReservationAgency: fee 설정
    end
    
    ReservationAgency ->> Reservation: new Reservation(customer, screening, fee, audienceCount)
    Reservation -->> ReservationAgency: Reservation 객체 생성
    ReservationAgency -->> Client: Reservation 반환
```

### 영화 예매 (간소화 버전)

```mermaid
sequenceDiagram
    actor Client
    participant ReservationAgency
    participant Screening
    participant Movie
    participant DiscountCondition
    participant Reservation
    title 영화 예매 간소화 시퀀스 다이어그램
    
    Client ->> ReservationAgency: reserve(screening, customer, audienceCount)
    
    ReservationAgency ->> Screening: getMovie()
    Screening -->> ReservationAgency: movie
    
    ReservationAgency ->> Movie: getDiscountConditions()
    Movie -->> ReservationAgency: discountConditions
    
    loop 할인 조건 검증
        ReservationAgency ->> DiscountCondition: 할인 조건 타입 확인
        ReservationAgency ->> Screening: 상영 정보 조회
        ReservationAgency ->> ReservationAgency: 할인 조건 만족 여부 확인
    end
    
    ReservationAgency ->> Movie: 영화 타입 및 요금 정보 조회
    Movie -->> ReservationAgency: movieType, fee, discountAmount/discountPercent
    
    ReservationAgency ->> ReservationAgency: 최종 요금 계산
    
    ReservationAgency ->> Reservation: Reservation 객체 생성
    Reservation -->> ReservationAgency: reservation
    
    ReservationAgency -->> Client: Reservation 반환
```

### 할인 조건 검증 상세 (PERIOD 타입)

```mermaid
sequenceDiagram
    participant ReservationAgency
    participant Screening
    participant DiscountCondition
    title PERIOD 타입 할인 조건 검증 시퀀스 다이어그램
    
    ReservationAgency ->> DiscountCondition: getType()
    DiscountCondition -->> ReservationAgency: PERIOD
    
    ReservationAgency ->> Screening: getWhenScreened()
    Screening -->> ReservationAgency: LocalDateTime 반환
    
    ReservationAgency ->> DiscountCondition: getDayOfWeek()
    DiscountCondition -->> ReservationAgency: DayOfWeek 반환
    
    ReservationAgency ->> DiscountCondition: getStartTime()
    DiscountCondition -->> ReservationAgency: LocalTime 반환
    
    ReservationAgency ->> DiscountCondition: getEndTime()
    DiscountCondition -->> ReservationAgency: LocalTime 반환
    
    ReservationAgency ->> ReservationAgency: 요일, 시작시간, 종료시간 검증
    ReservationAgency ->> ReservationAgency: discountable = true/false 결정
```

### 할인 조건 검증 상세 (SEQUENCE 타입)

```mermaid
sequenceDiagram
    participant ReservationAgency
    participant Screening
    participant DiscountCondition
    title SEQUENCE 타입 할인 조건 검증 시퀀스 다이어그램
    
    ReservationAgency ->> DiscountCondition: getType()
    DiscountCondition -->> ReservationAgency: SEQUENCE
    
    ReservationAgency ->> Screening: getSequence()
    Screening -->> ReservationAgency: sequence 반환
    
    ReservationAgency ->> DiscountCondition: getSequence()
    DiscountCondition -->> ReservationAgency: sequence 반환
    
    ReservationAgency ->> ReservationAgency: sequence 비교
    ReservationAgency ->> ReservationAgency: discountable = true/false 결정
```

### 할인 금액 계산 상세

```mermaid
sequenceDiagram
    participant ReservationAgency
    participant Movie
    title 할인 금액 계산 시퀀스 다이어그램
    
    ReservationAgency ->> Movie: getMovieType()
    Movie -->> ReservationAgency: movieType 반환
    
    alt AMOUNT_DISCOUNT
        ReservationAgency ->> Movie: getDiscountAmount()
        Movie -->> ReservationAgency: discountAmount 반환
        ReservationAgency ->> ReservationAgency: discountAmount = 할인 금액
    else PERCENT_DISCOUNT
        ReservationAgency ->> Movie: getFee()
        Movie -->> ReservationAgency: fee 반환
        ReservationAgency ->> Movie: getDiscountPercent()
        Movie -->> ReservationAgency: discountPercent 반환
        ReservationAgency ->> ReservationAgency: discountAmount = fee * discountPercent
    else NONE_DISCOUNT
        ReservationAgency ->> ReservationAgency: discountAmount = ZERO
    end
    
    ReservationAgency ->> Movie: getFee()
    Movie -->> ReservationAgency: fee 반환
    
    ReservationAgency ->> ReservationAgency: 최종 요금 = fee - discountAmount
```

