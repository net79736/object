package ch04_solid_principles.after.apec.stock_delivery;

public enum Grade {
    VIP(3),
    GOLD(2),
    SILVER(1),
    BRONZE(0);

    private int level;

    Grade(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return level;
    }

    /**
     * VIP 등급인지 여부를 반환합니다.
     * @return
     */
    public boolean isVip() {
        return level == VIP.getLevel();
    }
}
