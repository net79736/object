package v1;

/**
 * 관람객 정보를 나타내는 클래스
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-01
 */
public class Audience {
    // 가방
    private Bag bag;

    /**
     * 생성자로 초기화된 관람객 정보를 반환한다.
     * @param bag 가방
     */
    public Audience(Bag bag) {
        this.bag = bag;
    }

    /**
     * 가방을 반환한다.
     * @return 가방
     */
    public Bag getBag() {
        return bag;
    }
}
