package apec.test1;

public enum Grade {
    VIP(3),
    GOLD(2),
    SILVER(1),
    BRONZE(0);

    private int level;

    Grade(int level) {
        this.level = level;
    }

    public boolean isVip() {
        return level == VIP.getLevel();
    }

    public int getLevel() {
        return level;
    }
}
