package apec.test5.common;

import apec.test5.point.Point;

public class User {
    private Point point;
    private String email;
    private String phone;
    private String deviceToken;

    public User(Point point, String email, String phone, String deviceToken) {
        this.point = point;
        this.email = email;
        this.phone = phone;
        this.deviceToken = deviceToken;
    }

    public Point getPoint() {
        return point;
    }

    public void increasePoint(int point) {
        this.point.increase(point);
    }

    public void decreasePoint(int point) {
        this.point.decrease(point);
    }

    public void checkMaxPoint(int point) {
        this.point.checkMaxPoint(point);
    }
}
