# 알림 시스템 리팩토링: 상속에서 합성으로

## 📝 문제 분석: 상속 구조의 문제점

### 1. 조합 폭발 (Combinatorial Explosion) 문제

상속 구조에서는 여러 채널을 조합해야 하는 경우, 모든 조합에 대한 클래스를 만들어야 합니다.

**문제 상황:**
- 중요 알림: 이메일 + SMS
- 마케팅 알림: Push + 이메일  
- 긴급 알림: SMS + Push + 이메일

**상속 구조의 문제:**
```java
// 모든 조합에 대한 클래스가 필요함
class EmailSmsNotification extends Notification { }
class EmailPushNotification extends Notification { }
class SmsPushNotification extends Notification { }
class EmailSmsPushNotification extends Notification { }
// ... 새로운 조합이 추가될 때마다 클래스가 폭발적으로 증가
```

**문제점:**
- 새로운 채널(카카오톡)이 추가되면 조합 수가 기하급수적으로 증가
- 3개 채널 → 7가지 조합
- 4개 채널 → 15가지 조합
- 5개 채널 → 31가지 조합

### 2. 다중 상속 불가능 문제

Java는 단일 상속만 지원하므로, 여러 채널의 기능을 동시에 상속받을 수 없습니다.

**문제 상황:**
```java
// 불가능한 구조
class ImportantNotification extends EmailNotification, SmsNotification { } // ❌ 불가능

// 해결을 위한 우회 방법들
class ImportantNotification extends EmailNotification {
    private SmsNotification smsNotification; // 합성을 사용해야 함
    // 이미 상속을 사용했으므로 합성 패턴을 제대로 활용하기 어려움
}
```

**문제점:**
- 상속과 합성을 혼용하게 되어 일관성 없는 설계
- 코드 중복 발생
- 유지보수 어려움

### 3. 부가 기능 추가의 어려움 (Cross-Cutting Concerns)

재시도, 로깅, 타임아웃 같은 부가 기능을 추가하려면 각 알림 클래스마다 중복 코드를 작성해야 합니다.

**문제 상황:**
```java
class EmailNotification extends Notification {
    @Override
    public void send() {
        // 재시도 로직
        for (int i = 0; i < 3; i++) {
            try {
                // 로깅
                log.info("이메일 전송 시도: " + i);
                
                // 타임아웃 체크
                long startTime = System.currentTimeMillis();
                sendEmail();
                if (System.currentTimeMillis() - startTime > 5000) {
                    throw new TimeoutException();
                }
                
                return; // 성공
            } catch (Exception e) {
                if (i == 2) throw e;
            }
        }
    }
}

// SMS, Push도 동일한 코드를 반복해야 함
class SmsNotification extends Notification {
    // 동일한 재시도, 로깅, 타임아웃 로직 반복...
}
```

**문제점:**
- DRY 원칙 위반 (코드 중복)
- 부가 기능 변경 시 모든 클래스 수정 필요
- 테스트 코드도 중복 작성 필요

### 4. 유연성 부족 문제

런타임에 알림 타입이나 채널을 동적으로 변경하기 어렵습니다.

**문제 상황:**
```java
// 컴파일 타임에 클래스가 고정됨
Notification notification = new EmailSmsNotification(); // 고정됨

// 사용자 설정에 따라 동적으로 채널을 변경하고 싶어도 불가능
// 사용자가 SMS를 차단하면? → 새로운 클래스 필요
```

**문제점:**
- 사용자 설정 변경에 대응하기 어려움
- A/B 테스트나 실험적 기능 추가가 어려움
- 설정 기반 동적 구성 불가능

## ✅ 해결책: 합성 패턴 (Composition Pattern)

### 핵심 아이디어

1. **각 채널을 독립적인 객체로 분리**
   - EmailChannel, SmsChannel, PushChannel
   - 각 채널은 자신의 전송 로직만 담당

2. **알림은 여러 채널을 조합(합성)하여 사용**
   - ImportantNotification = EmailChannel + SmsChannel
   - MarketingNotification = PushChannel + EmailChannel
   - UrgentNotification = EmailChannel + SmsChannel + PushChannel

3. **부가 기능은 데코레이터 패턴으로 추가**
   - RetryHandler, LoggingHandler, TimeoutHandler
   - 각 채널에 동적으로 래핑 가능

### 장점

1. **확장성**: 새로운 채널 추가 시 기존 코드 수정 불필요
2. **유연성**: 런타임에 채널 조합 변경 가능
3. **재사용성**: 부가 기능을 독립적으로 재사용 가능
4. **테스트 용이성**: 각 컴포넌트를 독립적으로 테스트 가능

