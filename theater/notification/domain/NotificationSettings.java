package notification.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 알림 설정 클래스
 * 사용자가 각 채널별로 알림 수신 여부를 설정할 수 있습니다.
 * 
 * 합성 패턴의 장점:
 * - 사용자가 특정 채널을 차단해도 다른 채널은 정상 동작
 * - 런타임에 설정 변경 가능
 */
public class NotificationSettings {
    private Set<String> enabledChannels; // 활성화된 채널 목록
    
    public NotificationSettings() {
        this.enabledChannels = new HashSet<>();
    }
    
    public NotificationSettings(Set<String> enabledChannels) {
        this.enabledChannels = enabledChannels != null ? new HashSet<>(enabledChannels) : new HashSet<>();
    }
    
    /**
     * 특정 채널이 활성화되어 있는지 확인합니다.
     * 
     * @param channelName 채널 이름 (EMAIL, SMS, PUSH 등)
     * @return 활성화되어 있으면 true, 활성화되어 있지 않으면 false
     */
    public boolean isChannelEnabled(String channelName) {
        return enabledChannels.contains(channelName);
    }
}

