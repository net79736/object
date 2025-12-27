# notification 프로젝트 클래스 다이어그램

## 전체 클래스 다이어그램

전체 클래스 다이어그램은 합성 패턴을 사용한 알림 전송 시스템을 보여줍니다.

```mermaid
classDiagram
    class NotificationService {
        + sendNotification(notification: Notification, user: User, message: String): void
    }

    class Notification {
        <<interface>>
        + send(user: User, message: String): void
    }

    class ImportantNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class MarketingNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class UrgentNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class NotificationChannel {
        <<interface>>
        + send(user: User, message: String): void
        + getChannelName(): String
    }

    class EmailChannel {
        + send(user: User, message: String): void
        + getChannelName(): String
    }

    class SmsChannel {
        + send(user: User, message: String): void
        + getChannelName(): String
    }

    class PushChannel {
        + send(user: User, message: String): void
        + getChannelName(): String
    }

    class User {
        - email: String
        - phone: String
        - deviceToken: String
        - notificationSettings: NotificationSettings
    }

    class NotificationSettings {
        - blockedChannels: Set~String~
        + isChannelEnabled(channelName: String): boolean
        + blockChannel(channelName: String): void
    }

    NotificationService --> Notification
    Notification <|.. ImportantNotification
    Notification <|.. MarketingNotification
    Notification <|.. UrgentNotification
    
    ImportantNotification *-- NotificationChannel : 합성
    MarketingNotification *-- NotificationChannel : 합성
    UrgentNotification *-- NotificationChannel : 합성
    
    NotificationChannel <|.. EmailChannel
    NotificationChannel <|.. SmsChannel
    NotificationChannel <|.. PushChannel
    
    NotificationService --> User
    ImportantNotification --> User
    MarketingNotification --> User
    UrgentNotification --> User
    
    User --> NotificationSettings
```

## 합성 패턴 구조

```mermaid
classDiagram
    class ImportantNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class MarketingNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class UrgentNotification {
        - channels: List~NotificationChannel~
        + send(user: User, message: String): void
    }

    class NotificationChannel {
        <<interface>>
        + send(user: User, message: String): void
    }

    class EmailChannel {
        + send(user: User, message: String): void
    }

    class SmsChannel {
        + send(user: User, message: String): void
    }

    class PushChannel {
        + send(user: User, message: String): void
    }

    ImportantNotification *-- EmailChannel : 합성
    ImportantNotification *-- SmsChannel : 합성
    
    MarketingNotification *-- PushChannel : 합성
    MarketingNotification *-- EmailChannel : 합성
    
    UrgentNotification *-- EmailChannel : 합성
    UrgentNotification *-- SmsChannel : 합성
    UrgentNotification *-- PushChannel : 합성
    
    NotificationChannel <|.. EmailChannel
    NotificationChannel <|.. SmsChannel
    NotificationChannel <|.. PushChannel
```

## 데코레이터 패턴 구조 (부가 기능)

```mermaid
classDiagram
    class NotificationChannel {
        <<interface>>
        + send(user: User, message: String): void
        + getChannelName(): String
    }

    class EmailChannel {
        + send(user: User, message: String): void
    }

    class RetryHandler {
        - channel: NotificationChannel
        - maxRetries: int
        + send(user: User, message: String): void
    }

    class LoggingHandler {
        - channel: NotificationChannel
        + send(user: User, message: String): void
    }

    class TimeoutHandler {
        - channel: NotificationChannel
        - timeoutMillis: long
        + send(user: User, message: String): void
    }

    NotificationChannel <|.. EmailChannel
    NotificationChannel <|.. RetryHandler
    NotificationChannel <|.. LoggingHandler
    NotificationChannel <|.. TimeoutHandler
    
    RetryHandler *-- NotificationChannel : 래핑
    LoggingHandler *-- NotificationChannel : 래핑
    TimeoutHandler *-- NotificationChannel : 래핑
```

## 도메인 클래스

```mermaid
classDiagram
    class User {
        - email: String
        - phone: String
        - deviceToken: String
        - notificationSettings: NotificationSettings
        + getEmail(): String
        + getPhone(): String
        + getDeviceToken(): String
        + getNotificationSettings(): NotificationSettings
    }

    class NotificationSettings {
        - blockedChannels: Set~String~
        + isChannelEnabled(channelName: String): boolean
        + blockChannel(channelName: String): void
        + unblockChannel(channelName: String): void
    }

    User "1" -- "1" NotificationSettings
```

## 알림 전송 흐름

```mermaid
sequenceDiagram
    participant Client
    participant NotificationService
    participant ImportantNotification
    participant EmailChannel
    participant SmsChannel
    participant User
    participant NotificationSettings

    Client->>NotificationService: sendNotification(notification, user, message)
    NotificationService->>ImportantNotification: send(user, message)
    ImportantNotification->>NotificationSettings: isChannelEnabled("EMAIL")
    NotificationSettings-->>ImportantNotification: true
    ImportantNotification->>EmailChannel: send(user, message)
    EmailChannel-->>ImportantNotification: 완료
    
    ImportantNotification->>NotificationSettings: isChannelEnabled("SMS")
    NotificationSettings-->>ImportantNotification: true
    ImportantNotification->>SmsChannel: send(user, message)
    SmsChannel-->>ImportantNotification: 완료
    
    ImportantNotification-->>NotificationService: 완료
    NotificationService-->>Client: 완료
```

## 확장성 예시: 카카오톡 채널 추가

```mermaid
classDiagram
    class NotificationChannel {
        <<interface>>
        + send(user: User, message: String): void
    }

    class EmailChannel {
        + send(user: User, message: String): void
    }

    class SmsChannel {
        + send(user: User, message: String): void
    }

    class PushChannel {
        + send(user: User, message: String): void
    }

    class KakaoTalkChannel {
        + send(user: User, message: String): void
    }

    NotificationChannel <|.. EmailChannel
    NotificationChannel <|.. SmsChannel
    NotificationChannel <|.. PushChannel
    NotificationChannel <|.. KakaoTalkChannel
```

**확장 방법:**
1. `KakaoTalkChannel` 클래스만 추가 (기존 채널과 동일한 구조)
2. 알림 타입의 `channels` 리스트에 추가만 하면 됨
3. 기존 코드 수정 불필요

