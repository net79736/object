# 알림 시스템 리팩토링 프로젝트

## 📋 프로젝트 개요

이 프로젝트는 상속 기반 알림 시스템을 합성 패턴으로 리팩토링한 예제입니다.

### 문제점
- 상속 구조에서는 여러 채널 조합 시 클래스 폭발 발생
- 다중 상속 불가능으로 인한 구조적 제약
- 부가 기능(재시도, 로깅, 타임아웃) 추가 시 코드 중복

### 해결책
- 합성 패턴: 각 채널을 독립적인 객체로 분리하여 조합
- 데코레이터 패턴: 부가 기능을 독립적으로 관리

## 🏗️ 프로젝트 구조

```
notification/
├── domain/                    # 도메인 모델
│   ├── User.java
│   └── NotificationSettings.java
├── channel/                   # 전송 채널 (합성의 핵심)
│   ├── intf/
│   │   └── NotificationChannel.java
│   ├── EmailChannel.java
│   ├── SmsChannel.java
│   ├── PushChannel.java
│   └── KakaoTalkChannel.java  # 확장성 예시
├── notification/              # 알림 타입별 조합
│   ├── intf/
│   │   └── Notification.java
│   ├── ImportantNotification.java    # 이메일 + SMS
│   ├── MarketingNotification.java     # Push + 이메일
│   ├── UrgentNotification.java        # 모든 채널
│   └── ExtendedUrgentNotification.java # 카카오톡 포함
├── feature/                   # 부가 기능
│   ├── retry/
│   │   └── RetryHandler.java
│   ├── logging/
│   │   └── LoggingHandler.java
│   └── timeout/
│       └── TimeoutHandler.java
├── enums/
│   └── NotificationTypeEnum.java
├── NotificationService.java
├── NotificationTest.java
└── docs/
    ├── CLASS_DIAGRAM.md
    ├── REFACTORING.md
    └── README.md
```

## 🎯 주요 설계 원칙

### 1. 합성 패턴 (Composition Pattern)
- 각 채널을 독립적인 객체로 분리
- 알림 타입은 여러 채널을 조합하여 사용
- 런타임에 채널 조합 변경 가능

### 2. 데코레이터 패턴 (Decorator Pattern)
- 재시도, 로깅, 타임아웃 기능을 독립적으로 관리
- 필요할 때만 채널에 래핑하여 사용
- 기능 추가 시 기존 코드 수정 불필요

### 3. DIP (의존성 역전 원칙)
- 구체적인 구현체가 아닌 인터페이스에 의존
- `NotificationChannel` 인터페이스를 통해 채널 추상화

### 4. OCP (개방-폐쇄 원칙)
- 새로운 채널 추가 시 기존 코드 수정 불필요
- 확장에는 열려있고 수정에는 닫혀있음

### 5. SRP (단일 책임 원칙)
- 각 클래스는 하나의 책임만 가짐
- 채널은 전송만, 핸들러는 부가 기능만 담당

## 🚀 사용 예시

### 기본 사용

```java
// 사용자 생성
User user = new User(
    "user@example.com",
    "010-1234-5678",
    "device-token-123",
    new NotificationSettings()
);

// 중요 알림 전송 (이메일 + SMS)
Notification notification = new ImportantNotification();
NotificationService service = new NotificationService();
service.sendNotification(notification, user, "주문이 완료되었습니다.");
```

### 채널 차단

```java
// SMS 차단
NotificationSettings settings = new NotificationSettings();
settings.blockChannel("SMS");

User user = new User(
    "user@example.com",
    "010-1234-5678",
    "device-token-123",
    settings
);

// 마케팅 알림 전송 (Push + 이메일만, SMS는 제외)
Notification notification = new MarketingNotification();
service.sendNotification(notification, user, "특별 할인 쿠폰이 발급되었습니다!");
```

### 부가 기능 추가

```java
// 재시도 기능 추가
NotificationChannel emailChannel = new EmailChannel();
NotificationChannel retryChannel = new RetryHandler(emailChannel, 3);

// 로깅 기능 추가
NotificationChannel loggingChannel = new LoggingHandler(retryChannel);

// 타임아웃 기능 추가
NotificationChannel timeoutChannel = new TimeoutHandler(loggingChannel, 5000);
```

## 📊 테스트 시나리오

`NotificationTest.java`에서 다음 시나리오를 테스트합니다:

1. **중요한 주문 완료 알림**: 이메일 + SMS 동시 전송
2. **마케팅 쿠폰 알림**: Push + 이메일 전송 (SMS 차단됨)
3. **긴급 시스템 점검 알림**: 모든 채널 동시 전송
4. **확장성 검증**: 카카오톡 채널 추가 예시

## 🔍 확장성 검증

### 새로운 채널 추가 시

1. `NotificationChannel` 인터페이스를 구현하는 새 클래스 생성
2. 알림 타입의 `channels` 리스트에 추가

**예시: 카카오톡 채널 추가**
```java
// 1. KakaoTalkChannel 클래스 추가 (기존 채널과 동일한 구조)
public class KakaoTalkChannel implements NotificationChannel { ... }

// 2. 알림 타입에 추가
this.channels = Arrays.asList(
    new EmailChannel(),
    new SmsChannel(),
    new PushChannel(),
    new KakaoTalkChannel()  // 추가만 하면 됨!
);
```

**기존 코드 수정 불필요:**
- ✅ EmailChannel, SmsChannel, PushChannel 수정 불필요
- ✅ NotificationService 수정 불필요
- ✅ 부가 기능 핸들러 수정 불필요

## 📚 참고 문서

- [REFACTORING.md](docs/REFACTORING.md): 문제 분석 및 해결 과정
- [CLASS_DIAGRAM.md](docs/CLASS_DIAGRAM.md): 클래스 다이어그램

## 🎓 학습 포인트

1. **상속 vs 합성**: 언제 상속을 사용하고 언제 합성을 사용해야 하는가?
2. **조합 폭발 문제**: 상속 구조에서 발생하는 문제와 해결 방법
3. **데코레이터 패턴**: 부가 기능을 독립적으로 관리하는 방법
4. **확장성**: 새로운 기능 추가 시 기존 코드 수정을 최소화하는 방법

