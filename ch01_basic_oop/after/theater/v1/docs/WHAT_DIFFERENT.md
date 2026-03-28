# v0 vs v1 차이점

## 핵심 변경사항

**v0**: `ReservationAgency`가 모든 로직을 직접 처리
- 할인 조건 검증, 할인 요금 계산, 요금 계산 등 모든 로직이 `ReservationAgency`에 집중

**v1**: 각 객체가 자신의 책임을 가지도록 분산
- 할인 조건 검증 → `DiscountCondition.isDiscountable()`
- 할인 요금 계산 → `Movie.calculateAmountDiscountedFee()` 등
- 할인 가능 여부 검증 → `Movie.isDiscountable()`
- 요금 계산 → `Screening.calculateFee()`

## 결과

- `ReservationAgency`는 단순히 `Screening.calculateFee()`만 호출
- 각 객체가 자신의 데이터와 행동을 함께 관리 (응집도 향상)
- 새로운 할인 정책 추가 시에도 `Screening`만 수정하면 됨

