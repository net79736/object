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
    private Set<String> blockedChannels; // 차단된 채널 목록
    
    public NotificationSettings() {
        this.blockedChannels = new HashSet<>();
    }
    
    public NotificationSettings(Set<String> blockedChannels) {
        this.blockedChannels = blockedChannels != null ? new HashSet<>(blockedChannels) : new HashSet<>();
    }
    
    /**
     * 특정 채널이 활성화되어 있는지 확인합니다.
     * 
     * @param channelName 채널 이름 (EMAIL, SMS, PUSH 등)
     * @return 차단되지 않았으면 true, 차단되었으면 false
     */
    public boolean isChannelEnabled(String channelName) {
        return !blockedChannels.contains(channelName);
    }
    
    /**
     * 채널을 차단합니다.
     * 
     * @param channelName 차단할 채널 이름
     */
    public void blockChannel(String channelName) {
        blockedChannels.add(channelName);
    }
    
    /**
     * 채널 차단을 해제합니다.
     * 
     * @param channelName 차단 해제할 채널 이름
     */
    public void unblockChannel(String channelName) {
        blockedChannels.remove(channelName);
    }
    
    /**
     * 모든 채널이 차단되었는지 확인합니다.
     * 
     * @return 모든 채널이 차단되었으면 true
     */
    public boolean areAllChannelsBlocked() {
        return blockedChannels.size() >= 3; // EMAIL, SMS, PUSH 모두 차단
    }
}

